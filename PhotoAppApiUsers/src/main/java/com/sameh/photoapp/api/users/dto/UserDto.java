package com.sameh.photoapp.api.users.dto;

import java.io.Serializable;
import java.util.List;

import com.sameh.photoapp.api.users.model.AlbumResponse;

public class UserDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1365995638784228274L;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String userId;
	private String encryptedPassword;
	private List<AlbumResponse> albums;
	
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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<AlbumResponse> getAlbums() {
		return albums;
	}
	public void setAlbums(List<AlbumResponse> albums) {
		this.albums = albums;
	}
	
}
