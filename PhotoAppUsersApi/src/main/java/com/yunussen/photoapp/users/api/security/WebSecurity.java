package com.yunussen.photoapp.users.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.yunussen.photoapp.users.api.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private Environment env;
	
	private final UserService usersService;		
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService usersService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//security yanlız  gateway üzerinden actım.
		http.csrf().disable();
		//http.authorizeRequests().antMatchers("/**").permitAll();  tüm isteklere actım
		
		http.authorizeRequests()		
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
		.anyRequest().authenticated().and()
		.addFilter(getAuthenticationFilter());		
		http.headers().frameOptions().disable();
	}
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(),usersService,env);
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
	}


}
