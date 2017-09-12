package com.apirest.resources;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.apirest.event.RecursoCriadoEvent;
import com.apirest.service.ServiceException;

public abstract class ResourceGeneric<T> {
	@Inject
	protected Logger logger;
	
	@Inject
	protected Event<RecursoCriadoEvent> eventRecursoCriado;
	
	@Context
	protected HttpServletResponse response;
	
	@Context
	protected UriInfo uriInfo;

	public abstract Response criar(T t) throws ServiceException ;

	public abstract Response atualizar(Long codigo, T t) throws ServiceException;

	public abstract Response remover(Long codigo) throws ServiceException;

	public abstract Response listar() throws ServiceException;

	public abstract Response buscarPorCodigo(Long codigo) throws ServiceException ;
	
	

}
