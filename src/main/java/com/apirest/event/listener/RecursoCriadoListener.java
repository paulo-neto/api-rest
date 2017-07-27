package com.apirest.event.listener;

import javax.enterprise.event.Observes;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriInfo;

import com.apirest.event.RecursoCriadoEvent;

public class RecursoCriadoListener {

	public void onRecursoCriadoEvent(@Observes RecursoCriadoEvent event) {
		HttpServletResponse response = event.getResponse();
		Long codigo = event.getCodigo();
		UriInfo uriInfo = event.getUriInfo();
		response.setHeader("Location", uriInfo.getAbsolutePath().toString().concat("/").concat(codigo.toString()));
	}
}
