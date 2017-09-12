package com.apirest.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.apirest.models.Perfil;

public class PerfilRepository extends GenericRepositoryImpl<Perfil> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Perfil> findAll() {
		List<Perfil> retorno = new ArrayList<>();
		StringBuilder consulta = new StringBuilder("select p from Perfil p");
		try{
			TypedQuery<Perfil> query = entityManager.createQuery(consulta.toString(), Perfil.class);
			retorno = query.getResultList();
		}catch (Exception e) {
			logger.error(ExceptionUtils.getRootCauseMessage(e),e);
		}
		return retorno;
	}
}
