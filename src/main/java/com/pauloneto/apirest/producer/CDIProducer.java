package com.pauloneto.apirest.producer;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author paulo.antonio@fornecedores.sicoob.com.br
 * @version 0.1 - 29 de out de 2019
 */
public class CDIProducer {

	@PersistenceContext(unitName = "paulonetoPU")
	private EntityManager emPauloNetoPU;

	@Produces 
	public EntityManager create() {
		return emPauloNetoPU;
	}
}
