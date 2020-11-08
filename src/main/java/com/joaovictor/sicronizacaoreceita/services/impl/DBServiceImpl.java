package com.joaovictor.sicronizacaoreceita.services.impl;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joaovictor.sicronizacaoreceita.domain.Usuario;
import com.joaovictor.sicronizacaoreceita.repositories.UsuarioRepository;

@Service
public class DBServiceImpl {
	// TODO Implementar testes
	
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void instanciateTestDataBase() throws ParseException {
		
		Usuario usuario = new Usuario(null, "Maria Silva", "teste@gmail.com", pe.encode("123"));
		
		usuarioRepository.saveAll(Arrays.asList(usuario));
	}
	
}