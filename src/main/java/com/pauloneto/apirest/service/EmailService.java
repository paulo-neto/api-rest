package com.pauloneto.apirest.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmailService {

    private static final int SMTP_PORT = 465;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    @Inject
    private Logger logger;

    public void sendTo(@Pattern(regexp = EMAIL_REGEX,message = "E-mail inválido: '${validatedValue}'") String to, String text){
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(SMTP_PORT);
            email.setAuthenticator(new DefaultAuthenticator("pasvsi", "java231074"));
            email.setSSLOnConnect(true);
            email.setFrom("pasvsi@gmail.com");
            email.setSubject("TestMail Wwith ActiveMQ MDB");
            email.setMsg(text);
            email.addTo(to);
            email.send();
        } catch (EmailException e) {
            logger.log(Level.SEVERE, ExceptionUtils.getRootCauseMessage(e),ExceptionUtils.getRootCause(e));
        }
    }

}
