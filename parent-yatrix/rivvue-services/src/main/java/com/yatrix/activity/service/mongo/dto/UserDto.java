package com.yatrix.activity.service.mongo.dto;

import java.io.Serializable;

import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID = -5488702255320352709L;
	private String id;
	private String firstName;
	private String lastName;
	private String name;
	private String username;
	private String password;
	private String repassword;
	private String role;
	private PROFILETYPE srcProfileType;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public PROFILETYPE getSrcProfileType() {
		return srcProfileType;
	}
	public void setSrcProfileType(PROFILETYPE srcProfileType) {
		this.srcProfileType = srcProfileType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
}
