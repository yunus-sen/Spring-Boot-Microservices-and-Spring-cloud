package com.yunussen.photoapp.users.api.io.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yunussen.photoapp.users.api.ui.model.response.AlbumResponseModel;

@FeignClient(name = "albums-ws",fallbackFactory =  AlbumsFallbackFactory.class)
public interface AlbumServiceClient {

	@GetMapping(value = "/users/{id}/albums")
	public List<AlbumResponseModel> getAlbums(@PathVariable String id);

}

@Component
class AlbumsFallbackFactory implements FallbackFactory<AlbumServiceClient> {

	@Override
	public AlbumServiceClient create(Throwable cause) {
		
		return new AlbumsFallback(cause);
	}	
}


class AlbumsFallback implements AlbumServiceClient {

	private final Throwable cause;
	public AlbumsFallback(Throwable cause) {
		this.cause=cause;
	}
	
	@Override
	public List<AlbumResponseModel> getAlbums(String id) {
		
		//burada hataları loglayarak ayazdırabilirsin causes icin hatalar var.
		return new ArrayList<>();
	}
	
}
