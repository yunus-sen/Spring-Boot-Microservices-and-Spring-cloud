package com.yunussen.photoapp.users.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yunussen.photoapp.users.api.io.entity.UserEntity;
import com.yunussen.photoapp.users.api.io.repository.AlbumServiceClient;
import com.yunussen.photoapp.users.api.io.repository.UserRepository;
import com.yunussen.photoapp.users.api.shared.dto.UserDto;
import com.yunussen.photoapp.users.api.ui.model.response.AlbumResponseModel;

import feign.FeignException;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userPerository;
	// @Autowired
	// private RestTemplate restTemplate;

	@Autowired
	private AlbumServiceClient albumServiceClient;

	@Override
	public UserDto userCreate(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()));
		UserEntity storedUser = modelMapper.map(userDetails, UserEntity.class);
		storedUser = userPerository.save(storedUser);
		return modelMapper.map(storedUser, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity reqUser = userPerository.findByEmail(email);
		if (reqUser == null)
			throw new UsernameNotFoundException("password or email invalid! ");

		return new User(reqUser.getEmail(), reqUser.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailByEmail(String email) {
		UserEntity storUser = userPerository.findByEmail(email);
		if (storUser == null)
			throw new UsernameNotFoundException("user was not found. ");
		return modelMapper.map(storUser, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity storedUser = userPerository.findByUserId(userId);
		if (storedUser == null)
			throw new UsernameNotFoundException("user id is wrong");
		UserDto dto = modelMapper.map(storedUser, UserDto.class);

		/*
		 * Rest tampleta icin String albumsUrl =
		 * String.format("http://ALBUMS-WS/users/%s/albums", userId);
		 * 
		 * ResponseEntity<List<AlbumResponseModel>> albumslistResponse =
		 * restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new
		 * ParameterizedTypeReference<List<AlbumResponseModel>>() {
		 * 
		 * });
		 * 
		 * List<AlbumResponseModel> albumList = albumslistResponse.getBody();
		 */

		log.info("before calling albums microservice. ");
		
		// FeignCLient
		List<AlbumResponseModel> albumList=	albumList = albumServiceClient.getAlbums(userId);
		

		dto.setAlbums(albumList);
		return dto;
	}

}
