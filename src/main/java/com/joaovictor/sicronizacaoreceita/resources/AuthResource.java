package com.joaovictor.sicronizacaoreceita.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joaovictor.sicronizacaoreceita.domain.JwtRequest;
import com.joaovictor.sicronizacaoreceita.domain.JwtResponse;
import com.joaovictor.sicronizacaoreceita.services.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		JwtResponse jwtResponse = authService.authenticate(jwtRequest);
		return ResponseEntity.ok(jwtResponse);
	}

	
}
