package com.yunussen.photoapp.users.api.ui.controller;



import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


import com.yunussen.photoapp.users.api.service.UserService;
import com.yunussen.photoapp.users.api.shared.dto.UserDto;
import com.yunussen.photoapp.users.api.ui.model.request.CreateUserRequestModel;
import com.yunussen.photoapp.users.api.ui.model.response.UserCreateResponseModel;
import com.yunussen.photoapp.users.api.ui.model.response.UserResponseModel;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

	@Autowired
	private  Environment env;
	@Autowired
	private UserService userService;	
	@Autowired
	private ModelMapper modelMapper;
	


	
	@GetMapping(path = "/status/check")
	public String status() {
		return "it working on port "+ env.getProperty("local.server.port")+" with token=  "+env.getProperty("token.secret");
	}
	
	
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
				,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserCreateResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		UserCreateResponseModel returnValue;
		UserDto dto=modelMapper.map(userDetails, UserDto.class);
		dto=userService.userCreate(dto);
		returnValue=modelMapper.map(dto, UserCreateResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@GetMapping(value="/{userId}"
			,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
			,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponseModel> getUserDetails(@PathVariable String userId){
		
		UserDto userDto=userService.getUserByUserId(userId);
		UserResponseModel returnValue=modelMapper.map(userDto,UserResponseModel.class);		
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
}
