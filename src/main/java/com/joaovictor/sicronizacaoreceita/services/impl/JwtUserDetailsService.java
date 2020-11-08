package com.joaovictor.sicronizacaoreceita.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joaovictor.sicronizacaoreceita.components.Messages;
import com.joaovictor.sicronizacaoreceita.domain.Usuario;
import com.joaovictor.sicronizacaoreceita.services.UsuarioService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	// TODO Implementar testes

	@Autowired
	Messages messages;
	
	@Autowired
    private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findByEmail(email);
		
		if (usuario.getEmail().equals(email)) {
			return new User(email, usuario.getSenha(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException(messages.get("usuario.naoencontrado"));
		}
	}
}