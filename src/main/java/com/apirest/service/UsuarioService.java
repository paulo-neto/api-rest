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
import com.apirest.models.Perfil;
import com.apirest.models.Usuario;
import com.apirest.repository.RepositoryException;
import com.apirest.repository.impl.PerfilRepository;
import com.apirest.repository.impl.UsuarioRepository;
import com.apirest.util.AssertUtils;
import com.apirest.util.HashPasswordUtil;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UsuarioService {

	@Inject
	private Logger logger;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private PerfilRepository perfilRepository;
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario salvar(Usuario usuario) throws ServiceException{
		String hashedPwd = HashPasswordUtil.generateHash(usuario.getSenha());
		usuario.setSenha(hashedPwd);
		try{
			return usuarioRepository.save(usuario);
		}catch (RepositoryException e) {
			logger.error(ExceptionUtils.getRootCause(e), e);
			throw new ServiceException(e);
		}
	}


	public Usuario obterPorId(Long codigo) {
		return usuarioRepository.findId(Usuario.class, codigo);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Long codigo) throws ServiceException {
		Usuario u = usuarioRepository.findId(Usuario.class, codigo);
		try {
			usuarioRepository.delete(u);
		} catch (RepositoryException e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e),e);
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario atualizar(Long codigo, Usuario usuario) throws ServiceException {
		Usuario usuarioEncontrado = usuarioRepository.findId(Usuario.class, codigo);
		if (AssertUtils.isEmpty(usuarioEncontrado)) {
			String arg[] = {codigo.toString()};
			throw new ServiceException(KeyMesages.USUARIO_NAO_ENCONTRADO,arg);
		}
		try {
			BeanUtils.copyProperties(usuarioEncontrado, usuario);
			usuarioEncontrado.setId(codigo);
			return usuarioRepository.update(usuarioEncontrado);
		} catch (IllegalAccessException e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e),e);
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		} catch (InvocationTargetException e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e),e);
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		} catch (RepositoryException e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e),e);
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		}
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adicionaPerfilaUsuario(Long codigoPerfil, Long codigoUsuario) throws ServiceException {
		Perfil perfilEncontrado = perfilRepository.findId(Perfil.class, codigoPerfil);
		if(AssertUtils.isEmpty(perfilEncontrado)){
			String arg[] = {codigoPerfil.toString()};
			throw new ServiceException(KeyMesages.PERFIL_NAO_ENCONTRADO,arg);
		}
		Usuario usuarioEncontrado = usuarioRepository.findId(Usuario.class, codigoUsuario);
		if(AssertUtils.isEmpty(usuarioEncontrado)){
			String arg[] = {codigoUsuario.toString()};
			throw new ServiceException(KeyMesages.USUARIO_NAO_ENCONTRADO,arg);
		}
		usuarioEncontrado.getPerfis().add(perfilEncontrado);
		try {
			usuarioRepository.update(usuarioEncontrado);
		} catch (RepositoryException e) {
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		}
	}


	public List<Perfil> consultarPerfisDoUsuario(Long codigoUsuario){
		return perfilRepository.consultarPerfisDoUsuario(codigoUsuario);
	}
}
