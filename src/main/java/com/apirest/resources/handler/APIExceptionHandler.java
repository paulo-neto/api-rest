package com.apirest.resources.handler;

import java.io.Serializable;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;

@Provider
public class APIExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception arg0) {
		ErroMessage erro = new ErroMessage("Erro no servidor", ExceptionUtils.getRootCauseMessage(arg0));
		return Response.serverError().entity(erro).type(MediaType.APPLICATION_JSON).build();
	}

	public class ErroMessage implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String msgUser;
		private String msgDev;
		
		public ErroMessage(String msgUser, String msgDev) {
			this.msgUser = msgUser;
			this.msgDev = msgDev;
		}

		public ErroMessage() {}

		/**
		 * @return the msgUser
		 */
		public String getMsgUser() {
			return msgUser;
		}

		/**
		 * @param msgUser the msgUser to set
		 */
		public void setMsgUser(String msgUser) {
			this.msgUser = msgUser;
		}

		/**
		 * @return the msgDev
		 */
		public String getMsgDev() {
			return msgDev;
		}

		/**
		 * @param msgDev the msgDev to set
		 */
		public void setMsgDev(String msgDev) {
			this.msgDev = msgDev;
		}
	}
}
