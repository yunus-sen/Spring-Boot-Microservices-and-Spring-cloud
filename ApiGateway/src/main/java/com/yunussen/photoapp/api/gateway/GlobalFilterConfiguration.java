package com.yunussen.photoapp.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {

	private final Logger log=LoggerFactory.getLogger(GlobalFilterConfiguration.class);
	
	@Order(1)
	public GlobalFilter secondPreFilter() {
		return (exchange,chain)->{
			 log.info("my second pre filter");
		        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
		            log.info("my third post filter");
		        }));
		};
	}
	
	@Order(2)
	public GlobalFilter thirdPreFilter() {
		return (exchange,chain)->{
			 log.info("my third pre filter");
		        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
		            log.info("my second post filter");
		        }));
		};
	}

	@Order(3)
	public GlobalFilter fourthPreFilter() {
		return (exchange,chain)->{
			 log.info("my fourth pre filter");
		        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
		            log.info("my first post filter");
		        }));
		};
	}

}
