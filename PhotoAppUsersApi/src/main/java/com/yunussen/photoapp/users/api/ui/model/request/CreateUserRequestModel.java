package com.yunussen.photoapp.users.api.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

	@NotNull(message = "first name cannot be null!")
	@Size(min = 2,message = "firt name not must be less than two character.")
	private String firstName;
	@NotNull(message = "last name cannot be null!")
	@Size(min = 2,max = 18,message = "last name  must be grass than 18 character and  less than two character.")
	private String lastName;
	@NotNull(message = "email cannot be null!")
	@Email
	private String email;
	@NotNull(message = "password cannot be null!")
	@Size(min = 2,max = 18,message = "password must be grass than 18 character and  less than two character.")
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public void setName(String name) {
		this.firstName = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String userName) {
		this.lastName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
