package com.joaovictor.sicronizacaoreceita.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.joaovictor.sicronizacaoreceita.domain.Conta;
import com.joaovictor.sicronizacaoreceita.domain.Usuario;

public interface UsuarioService {

	public Usuario find(Integer id);
	
	public Usuario insert(Usuario obj);
	
	public List<Usuario> findAll();
	
	public Usuario findByEmail(String email);
}
