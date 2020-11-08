package com.joaovictor.sicronizacaoreceita.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaovictor.sicronizacaoreceita.components.Messages;
import com.joaovictor.sicronizacaoreceita.domain.JwtRequest;
import com.joaovictor.sicronizacaoreceita.domain.JwtResponse;
import com.joaovictor.sicronizacaoreceita.domain.Usuario;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoEncontradoException;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;
import com.joaovictor.sicronizacaoreceita.repositories.UsuarioRepository;
import com.joaovictor.sicronizacaoreceita.security.JwtTokenUtil;
import com.joaovictor.sicronizacaoreceita.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	Messages messages;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new NaoEncontradoException(messages.get("usuario.naoencontrado")));
	}
	
	@Transactional
	@Override
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	@Override
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Usuario findByEmail(String email) {
		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new NaoEncontradoException(messages.get("usuario.naoencontrado"));
		}
		return obj;
	}
	
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
			throw new RequisicaoInvalidaException(messages.get("Requisição inválida"));
		}
	}
}
