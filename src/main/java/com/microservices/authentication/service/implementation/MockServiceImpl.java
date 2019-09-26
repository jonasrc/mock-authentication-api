package com.microservices.authentication.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.authentication.domain.User;
import com.microservices.authentication.service.MockService;

@Service
public class MockServiceImpl implements MockService{
	
	private List<User> userList;
	
	@PostConstruct
	private void createMockUserList() {
		this.userList = new ArrayList<>();
		userList.add(new User("Usuário 1", "usuario1", "teste123"));
		userList.add(new User("Usuário 2", "usuario2", "teste123"));
		userList.add(new User("Usuário 3", "usuario3", "teste123"));
	}

	@Override
	public String authenticate(String login, String password) {
		User user = this.userList
				.stream()
				.filter(element -> element.getLogin().equals(login) && element.getPassword().equals(password))
				.findAny()
				.orElse(null);
		
		return user != null ? "authorized" : "unauthorized";
		
	}
	
	@Override
	public List<User> getUserList(){
		return this.userList;
	}
	
	@Override
	public User createUser(String name, String login, String password) {
		User user = new User(name, login, password);
		this.userList.add(user);
		return user;
	}
}
