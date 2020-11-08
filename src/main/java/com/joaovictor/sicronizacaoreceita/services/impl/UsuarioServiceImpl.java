package com.joaovictor.sicronizacaoreceita.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaovictor.sicronizacaoreceita.domain.Usuario;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoEncontradoException;
import com.joaovictor.sicronizacaoreceita.repositories.UsuarioRepository;
import com.joaovictor.sicronizacaoreceita.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
//	@Autowired
//	private BCryptPasswordEncoder pe;
	
	@Override
	public Usuario find(Integer id) {
		
//		UserSS user = AuthService.authenticated();
//		if (user==null || !id.equals(user.getId())) {
//			throw new NaoAutorizadoException("Acesso negado");
//		}

		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new NaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	@Transactional
	@Override
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	
//	public void delete(Integer id) {
//		find(id);
//		try {
//			repo.deleteById(id);
//		} catch(DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas.");
//		}
//	}
	
	@Override
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Usuario findByEmail(String email) {
//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new NaoEncontradoException(
					"Objeto não encontrado! email: " + email + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
}
