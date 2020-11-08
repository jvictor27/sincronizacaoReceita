package com.joaovictor.sicronizacaoreceita.services;

import com.joaovictor.sicronizacaoreceita.domain.JwtRequest;
import com.joaovictor.sicronizacaoreceita.domain.JwtResponse;

public interface AuthService {
	
	public JwtResponse authenticate(JwtRequest jwtRequest) throws Exception;
}
