package com.apirest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.apirest.event.RecursoCriadoEvent;
import com.apirest.models.Usuario;
import com.apirest.repository.RepositoryException;
import com.apirest.service.ServiceException;
import com.apirest.service.UsuarioService;
import com.apirest.util.AssertUtils;


@Path("/usuarios")
@Consumes(value = { MediaType.APPLICATION_JSON })
public class UsuarioResource extends ResourceGeneric<Usuario>{
	
	@Inject
	private UsuarioService usuarioService;

	@Path("/")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response criar(@Valid Usuario usuario) throws RepositoryException{
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		eventRecursoCriado.fire(new RecursoCriadoEvent(response,uriInfo, usuario.getId()));
		return Response.status(Status.CREATED).entity(usuarioSalvo).build();
	}
	
	@Path("/{codigo}")
	@PUT
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response atualizar(@PathParam("codigo") Long codigo, Usuario usuario)
			throws RepositoryException, ServiceException {
		Usuario usuAtualizado = usuarioService.atualizar(codigo,usuario);
		return Response.ok().entity(usuAtualizado).build();
	}
	
	@Path("/{codigo}")
	@DELETE
	@Override
	public Response remover(@PathParam("codigo") Long codigo)throws RepositoryException{
		usuarioService.remover(codigo);
		return Response.status(Status.OK).build();
	}

	@Path("/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response listar() throws RepositoryException {
		List<Usuario> retorno = usuarioService.listar();
		return AssertUtils.isEmpty(retorno) ? Response.status(Status.NO_CONTENT).build()
				: Response.status(Status.OK).entity(retorno).build();
	}
	
	@Path("/{codigo}")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response buscarPeloCodigo(@PathParam("codigo") Long codigo)throws RepositoryException{
		Usuario usuarioEncontrado = usuarioService.obterPorId(codigo);
		return AssertUtils.isEmpty(usuarioEncontrado) ? Response.status(Status.NO_CONTENT).build()
				: Response.status(Status.OK).entity(usuarioEncontrado).build();
	}
}
