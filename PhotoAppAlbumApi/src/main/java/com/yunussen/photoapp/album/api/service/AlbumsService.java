package com.yunussen.photoapp.album.api.service;

import java.util.List;

import com.yunussen.photoapp.album.api.data.AlbumEntity;



public interface AlbumsService {

	List<AlbumEntity> getAlbums(String userId);
}
