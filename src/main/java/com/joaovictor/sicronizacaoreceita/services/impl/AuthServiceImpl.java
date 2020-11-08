package com.joaovictor.sicronizacaoreceita.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.joaovictor.sicronizacaoreceita.components.Messages;
import com.joaovictor.sicronizacaoreceita.domain.JwtRequest;
import com.joaovictor.sicronizacaoreceita.domain.JwtResponse;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoEncontradoException;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;
import com.joaovictor.sicronizacaoreceita.security.JwtTokenUtil;
import com.joaovictor.sicronizacaoreceita.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	// TODO Implementar testes
	
	@Autowired
	Messages messages;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Override
	public JwtResponse authenticate(JwtRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			
			return new JwtResponse(token);
		} catch (DisabledException e) {
			throw new NaoEncontradoException(messages.get("usuario.naoencontrado"));
		} catch (BadCredentialsException e) {
			throw new RequisicaoInvalidaException(messages.get("requisicao.dadosinvalidos"));
		}
	}
}
