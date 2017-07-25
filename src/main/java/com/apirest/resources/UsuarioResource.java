package com.apirest.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.apirest.models.Product;
import com.apirest.models.Usuario;

@Path("/usuarios")
public class UsuarioResource {

	@Path("/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Usuario> exampleGet() {
		return new ArrayList<Usuario>();
	}

	@Path("/")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response create(Product product) throws URISyntaxException {
		
		return Response.created(new URI("products")).build();
	}
}
