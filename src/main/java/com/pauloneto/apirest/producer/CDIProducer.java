package com.pauloneto.apirest.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * @author paulo.antonio@fornecedores.sicoob.com.br
 * @version 0.1 - 29 de out de 2019
 */
public class CDIProducer {

	private static final String ACTIVEMQ_BROKER_URL = "tcp://localhost:61616?jms.rmIdFromConnectionId=true";

	@PersistenceContext(unitName = "paulonetoPU")
	private EntityManager emPauloNetoPU;

	@Produces 
	public EntityManager createEntityManager() {
		return emPauloNetoPU;
	}

	@Produces
	public ActiveMQConnectionFactory createMQConnectionFactory(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_BROKER_URL);
		factory.setTrustAllPackages(true);
		return factory;
	}

	@Produces
	public Logger createLogger(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
