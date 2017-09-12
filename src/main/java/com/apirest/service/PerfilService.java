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
import com.apirest.repository.RepositoryException;
import com.apirest.repository.impl.PerfilRepository;
import com.apirest.util.AssertUtils;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PerfilService {

	@Inject
	private Logger logger;
	
	@Inject
	private PerfilRepository perfilRepository;
	
	public List<Perfil> listar(){
		return perfilRepository.findAll();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Perfil salvar(Perfil perfil) throws ServiceException{
		try{
			return perfilRepository.save(perfil);
		}catch (RepositoryException e) {
			logger.error(ExceptionUtils.getRootCause(e), e);
			throw new ServiceException(e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Perfil atualizar(Long codigo, Perfil perfil) throws ServiceException {
		Perfil perfilEncontrado = perfilRepository.findId(Perfil.class, codigo);
		if (AssertUtils.isEmpty(perfilEncontrado)) {
			String arg[] = {codigo.toString()};
			throw new ServiceException(KeyMesages.USUARIO_NAO_ENCONTRADO,arg);
		}
		try {
			BeanUtils.copyProperties(perfilEncontrado, perfil);
			perfilEncontrado.setId(codigo);
			return perfilRepository.update(perfilEncontrado);
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
	public void remover(Long codigo) throws ServiceException {
		Perfil p = perfilRepository.findId(Perfil.class, codigo);
		try {
			perfilRepository.delete(p);
		} catch (RepositoryException e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e),e);
			throw new ServiceException(KeyMesages.ERRO_GENERICO,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public Perfil obterPorId(Long codigo) {
		return perfilRepository.findId(Perfil.class, codigo);
	}
}
