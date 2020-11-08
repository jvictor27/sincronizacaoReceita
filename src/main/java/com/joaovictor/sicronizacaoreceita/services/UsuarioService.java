package com.joaovictor.sicronizacaoreceita.services;

import java.util.List;

import com.joaovictor.sicronizacaoreceita.domain.JwtRequest;
import com.joaovictor.sicronizacaoreceita.domain.JwtResponse;
import com.joaovictor.sicronizacaoreceita.domain.Usuario;

public interface UsuarioService {

	public Usuario find(Integer id);
	
	public Usuario insert(Usuario obj);
	
	public List<Usuario> findAll();
	
	public Usuario findByEmail(String email);
	
	public JwtResponse authenticate(JwtRequest jwtRequest) throws Exception;
}
