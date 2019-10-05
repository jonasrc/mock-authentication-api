package com.microservices.authentication.rest;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.service.MockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "auth/v1/api")
@Api(tags = "Autenticação", value = "authenticationController", description="Operações relativas a autenticação de usuários")
public class AuthenticationController {
	
	@Autowired
	private MockService mockService;
	
	@GetMapping("/test")
	@ApiOperation(value = "Testar autenticação de usuários com request direto (basta passar um request vazio com Basic Auth)")
	public ResponseEntity<String> testAuthentication(HttpServletRequest request){
		String authorization = request.getHeader("Authorization");
	    String base64Credentials = authorization.substring("Basic".length()).trim();
		byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials.getBytes());
		String decodedAuth = new String(decodedBytes);
		String[] userData = decodedAuth.split(":");
		return ResponseEntity.ok().body(mockService.authenticate(userData[0], userData[1]));
	}
	
	@GetMapping("/authenticate")
	@ApiOperation(value = "Autenticar usuário a partir de login e senha criptografados em base64")
	public ResponseEntity<String> authenticateUser(
			@ApiParam(value = "Base64 contendo login e senha do usuário a autenticar", required = true)
			@RequestParam String base64){
	    String base64Credentials = base64.substring("Basic".length()).trim();
		byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials.getBytes());
		String decodedAuth = new String(decodedBytes);
		String[] userData = decodedAuth.split(":");
		return ResponseEntity.ok().body(mockService.authenticate(userData[0], userData[1]));
	}
}
