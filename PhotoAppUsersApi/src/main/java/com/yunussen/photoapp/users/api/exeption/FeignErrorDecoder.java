package com.yunussen.photoapp.users.api.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		switch (response.status()) {
		case 400:
			// return new BadRequestException();
		case 404:
			if (methodKey.contains("getAlbums")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Users albums are not found");
			}

		default:
			return new Exception("Generic error");
		}
	}
}
