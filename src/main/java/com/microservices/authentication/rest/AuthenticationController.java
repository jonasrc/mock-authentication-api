package com.microservices.authentication.rest;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.service.MockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/authentication")
@Api(tags = "Autenticação", value = "authenticationController", description="Operações relativas a autenticação de usuários")
public class AuthenticationController {
	
	@Autowired
	private MockService mockService;
	
	@GetMapping
	@ApiOperation(value = "Testar autenticação de usuários")
	public ResponseEntity<String> testAuthentication(HttpServletRequest request){
		String authorization = request.getHeader("Authorization");
	    String base64Credentials = authorization.substring("Basic".length()).trim();
		byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials.getBytes());
		String decodedAuth = new String(decodedBytes);
		String[] userData = decodedAuth.split(":");
		return ResponseEntity.ok().body(mockService.authenticate(userData[0], userData[1]));
	}
}
