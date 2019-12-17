package com.pauloneto.apirest.messaging.mdb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pauloneto.apirest.models.Usuario;
import com.pauloneto.apirest.service.EmailService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* @author paulo.antonio@fornecedores.sicoob.com.br 
* @version 0.1 - 30 de out de 2019
*/
 @MessageDriven(name="EmailMDB",
		activationConfig = {
				@ActivationConfigProperty(propertyName="destinationLookup", propertyValue="java:jboss/exported/jms/queue/queue.NEWUSEREMAIL"),
				@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
				@ActivationConfigProperty(propertyName="destination", propertyValue="queue.NEWUSEREMAIL"),
				@ActivationConfigProperty(propertyName="acknowledgeMode", propertyValue="Auto-acknowledge"),
				 @ActivationConfigProperty(propertyName = "user",propertyValue = "admin"),
				 @ActivationConfigProperty(propertyName = "password",propertyValue = "admin"),
				 @ActivationConfigProperty(propertyName = "connectionParameters",propertyValue = "host=localhost;port=61616;jms.rmIdFromConnectionId=true"),
				 @ActivationConfigProperty(propertyName = "connectorClassName",propertyValue = "org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory")

				})
@ResourceAdapter("activemq-rar.rar")
public class EmailMDB implements MessageListener {

 	@Inject
	private Logger logger;

 	@Inject
 	private EmailService emailService;

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			ObjectMapper om = new ObjectMapper();
			Usuario usuario = om.readValue(textMessage.getText(),Usuario.class);
			emailService.sendTo(usuario.getEmail(), String.format("Novo usuário criado %s, com o e-mail %s",usuario.getLogin(), usuario.getEmail()));
		} catch (JMSException | JsonParseException | JsonMappingException  e) {
			logger.log(Level.SEVERE, ExceptionUtils.getRootCauseMessage(e),ExceptionUtils.getRootCause(e));
		}catch (IOException e) {
			logger.log(Level.SEVERE, ExceptionUtils.getRootCauseMessage(e),ExceptionUtils.getRootCause(e));
		}
	}
}
