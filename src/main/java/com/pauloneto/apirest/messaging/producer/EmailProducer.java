package com.pauloneto.apirest.messaging.producer;
/**
* @author paulo.antonio@fornecedores.sicoob.com.br 
* @version 0.1 - 30 de out de 2019
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pauloneto.apirest.models.Usuario;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;

public class EmailProducer {

	@Inject
	private ActiveMQConnectionFactory connectionFactory;

	public void sendEmail(Usuario usuario) {
		sendMessage(usuario);
	}

    private void sendMessage(Usuario usuario) {
        try (Connection connection = connectionFactory.createConnection()){
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("queue.NEWUSEREMAIL");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(usuario);
            TextMessage message = session.createTextMessage(json);
            producer.send(message);
        } catch (JMSException | JsonProcessingException e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e),ExceptionUtils.getRootCause(e));
        }
    }
}
