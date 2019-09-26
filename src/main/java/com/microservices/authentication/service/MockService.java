package com.microservices.authentication.service;

import java.util.List;

import com.microservices.authentication.domain.User;

public interface MockService {
	public String authenticate(String login, String password);
	
	public List<User> getUserList();
	
	public User createUser(String name, String login, String password);
}
