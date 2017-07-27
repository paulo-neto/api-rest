package com.apirest.event;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriInfo;

public class RecursoCriadoEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private Long codigo;
	private UriInfo uriInfo;
	
	public RecursoCriadoEvent(HttpServletResponse response, UriInfo uriInfo, Long codigo) {
		this.response = response;
		this.codigo = codigo;
		this.uriInfo = uriInfo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public UriInfo getUriInfo() {
		return uriInfo;
	}

	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
}
