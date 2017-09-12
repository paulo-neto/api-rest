package com.apirest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import com.apirest.event.RecursoCriadoEvent;
import com.apirest.models.Perfil;
import com.apirest.service.PerfilService;
import com.apirest.service.ServiceException;
import com.apirest.util.AssertUtils;

@Path("perfis")
@Consumes(value = { MediaType.APPLICATION_JSON })
@ValidateRequest
public class PerfilResource extends ResourceGeneric<Perfil> {

	@Inject
	private PerfilService perfilService;
	
	
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response criar(Perfil perfil) throws ServiceException {
		Perfil perfilSalvo = perfilService.salvar(perfil);
		eventRecursoCriado.fire(new RecursoCriadoEvent(response,uriInfo, perfil.getId()));
		return Response.status(Status.CREATED).entity(perfilSalvo).build();
	}

	@Path("{codigo}")
	@PUT
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response atualizar(Long codigo, Perfil perfil) throws ServiceException {
		Perfil perfilAtualizado = perfilService.atualizar(codigo,perfil);
		return Response.ok().entity(perfilAtualizado).build();
	}

	@Path("{codigo}")
	@DELETE
	@Override
	public Response remover(Long codigo) throws ServiceException {
		perfilService.remover(codigo);
		return Response.status(Status.OK).build();
	}

	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response listar() throws ServiceException {
		List<Perfil> retorno = perfilService.listar();
		return AssertUtils.isEmpty(retorno) ? Response.status(Status.NO_CONTENT).build()
				: Response.status(Status.OK).entity(retorno).build();
	}

	@Path("{codigo}")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Response buscarPorCodigo(Long codigo) throws ServiceException {
		Perfil perfilEncontrado = perfilService.obterPorId(codigo);
		return AssertUtils.isEmpty(perfilEncontrado) ? Response.status(Status.NO_CONTENT).build()
				: Response.status(Status.OK).entity(perfilEncontrado).build();
	}

}
