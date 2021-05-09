package com.yunussen.photoapp.api.gateway;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;



import reactor.core.publisher.Mono;

@Component
public class MyPreFilter implements GlobalFilter,Ordered {

	private final Logger log= LoggerFactory.getLogger(MyPreFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		//exchange : server ile client arasında paket taşıyıcı (request-header -response path tutatr)
		log.info("global filter execute.");
		
	
		String requestPath=exchange.getRequest().getPath().toString();
		log.info("request path:"+ requestPath);
		
		HttpHeaders headers=exchange.getRequest().getHeaders();
		
		Set<String> HeaderNames=headers.keySet();
		
		HeaderNames.forEach((headerName)->{
			String headerValue=headers.getFirst(headerName);
			log.info(headerName +" "+ headerValue);
			
		});
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
		//ilk calışması icin ekledim.
	}

}
