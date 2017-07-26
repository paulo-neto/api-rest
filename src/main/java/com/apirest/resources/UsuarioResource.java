package com.apirest.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.apirest.mesages.KeyMesages;
import com.apirest.models.Product;
import com.apirest.models.Usuario;
import com.apirest.repository.RepositoryException;

@Path("/usuarios")
public class UsuarioResource {
	
	@Inject
	private Logger logger;

	@Path("/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Usuario> listaUsuarios() throws RepositoryException {
		
		throw new RepositoryException(KeyMesages.ERRO_GENERICO,"bla");
	}

	@Path("/")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response create(Product product) throws URISyntaxException {
		
		return Response.created(new URI("products")).build();
	}
}
