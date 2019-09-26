package com.microservices.authentication.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.domain.User;
import com.microservices.authentication.service.MockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "Usuários", value = "userController", description="Operações relativas a usuários (apenas para testes)")
public class UserController {
	@Autowired
	private MockService mockService;
	
	@GetMapping
	@ApiOperation(value = "Listar usuários cadastrados")
	public ResponseEntity<List<User>> getList(){
		return ResponseEntity.ok().body(mockService.getUserList());
	}
	
	@PostMapping
	@ApiOperation(value = "Criar usuário")
	public ResponseEntity<User> create(
			@ApiParam(value = "Nome", required = true)
			@RequestParam String name, 
			@ApiParam(value = "Login", required = true)
			@RequestParam String login, 
			@ApiParam(value = "Senha", required = true)
			@RequestParam String password) {
		User user = mockService.createUser(name, login, password);
		return ResponseEntity.created(URI.create(user.getId())).body(user);
	}
}
