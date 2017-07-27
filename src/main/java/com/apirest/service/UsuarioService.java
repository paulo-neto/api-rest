package com.apirest.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.apirest.models.Usuario;
import com.apirest.repository.RepositoryException;
import com.apirest.repository.impl.UsuarioRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario salvar(Usuario usuario) throws RepositoryException{
		return usuarioRepository.save(usuario);
	}


	public Usuario obterPorId(Long codigo) {
		return usuarioRepository.findId(Usuario.class, codigo);
	}
}
