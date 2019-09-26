package com.microservices.authentication.domain;

import java.util.UUID;

import com.microservices.authentication.util.Util;

public class User {
	private String id;
	
	private String name;
	
	private String login;
	
	private String password;
	
	private String createdAt;
	
	public User(String name, String login, String password) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.login = login;
		this.password = password;
		this.createdAt = Util.getCurrentDateTime();
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
