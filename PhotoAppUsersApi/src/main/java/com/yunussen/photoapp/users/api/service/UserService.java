package com.yunussen.photoapp.users.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yunussen.photoapp.users.api.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto userCreate(UserDto userDetails);
	UserDto getUserDetailByEmail(String email);
	UserDto getUserByUserId(String userId);
}
