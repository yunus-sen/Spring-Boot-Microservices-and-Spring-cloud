package com.yunussen.photoapp.api.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.slf4j.Logger;

import reactor.core.publisher.Mono;
@Component
public class MyPostFilter implements GlobalFilter,Ordered {

	private final Logger log=org.slf4j.LoggerFactory.getLogger(MyPostFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// işl istekde chain.filter(exchange) calışıp metoda gidiyor metod res döndükten sonra then calışır.  
		return chain.filter(exchange).then(Mono.fromRunnable(()->{
			//istek controller daki metodu calıştırdıktan sonra calışcak.
			log.info("Post-last-filter is execute.");
			
		}));
	}

	@Override
	public int getOrder() {
		
		return 0;
	}

}
