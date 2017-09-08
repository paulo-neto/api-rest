package com.apirest.resources.handler;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;

@Provider
public class APIExceptionHandler implements ExceptionMapper<Exception>, ConfiguraResponseExceptionHandler {

	@Override
	public Response toResponse(Exception arg0) {
		ErroMessage erro = new ErroMessage("Erro no servidor", ExceptionUtils.getRootCauseMessage(arg0));
		List<ErroMessage> erros = Arrays.asList(erro);
		return configurar(MediaType.APPLICATION_JSON_TYPE,erros, Status.INTERNAL_SERVER_ERROR);
	}
	
	
	/* (non-Javadoc)
	 * @see com.apirest.resources.handler.ConfiguraResponseExceptionHandler#configurar(javax.ws.rs.core.MediaType, java.lang.Object, javax.ws.rs.core.Response.Status)
	 */
	@Override
	public Response configurar(MediaType mediaType, List<ErroMessage> obj, Status statusHttp){
		return Response.status(statusHttp).type(mediaType).entity(obj).build();
	}
}
