package com.apirest.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.apirest.models.Usuario;

public class UsuarioRepository extends GenericRepositoryImpl<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Usuario> findAll() {
		List<Usuario> retorno = new ArrayList<>();
		StringBuilder consulta = new StringBuilder("select u from Usuario u");
		try{
			TypedQuery<Usuario> query = entityManager.createQuery(consulta.toString(), Usuario.class);
			retorno = query.getResultList();
		}catch (Exception e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e));
		}
		return retorno;
	}
	
	

}
