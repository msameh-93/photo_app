package com.sameh.photoapp.api.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {
	
	@NotNull(message= "Please provide first name")
	private String firstName;
	@NotNull(message= "Please provide last name")
	private String lastName;
	@NotNull(message= "Please provide password")
	@Size(min= 6, max=8, message= "Password must be between 6-8 characters long")
	private String password;
	@NotNull(message= "Please provide email")
	@Email
	private String email;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
