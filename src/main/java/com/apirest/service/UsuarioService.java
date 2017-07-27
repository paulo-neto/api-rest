package com.apirest.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.apirest.mesages.KeyMesages;
import com.apirest.models.Usuario;
import com.apirest.repository.RepositoryException;
import com.apirest.repository.impl.UsuarioRepository;
import com.apirest.util.AssertUtils;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UsuarioService {

	@Inject
	private Logger logger;
	
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Long codigo) throws RepositoryException {
		Usuario u = usuarioRepository.findId(Usuario.class, codigo);
		usuarioRepository.delete(u);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario atualizar(Long codigo, Usuario usuario) throws RepositoryException, ServiceException {
		Usuario usuarioEncontrado = usuarioRepository.findId(Usuario.class, codigo);
		if (AssertUtils.isEmpty(usuarioEncontrado)) {
			String arg[] = {codigo.toString()};
			throw new RepositoryException(KeyMesages.USUARIO_NAO_ENCONTRADO,arg);
		}
		try {
			BeanUtils.copyProperties(usuarioEncontrado, usuario);
		} catch (IllegalAccessException e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e));
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		} catch (InvocationTargetException e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e));
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		}
		usuarioEncontrado.setId(codigo);
		return usuarioRepository.update(usuarioEncontrado);
		
	}
}
