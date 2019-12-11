package com.pauloneto.apirest.resources;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pauloneto.apirest.models.Perfil;
import com.pauloneto.apirest.models.Usuario;
import com.pauloneto.apirest.service.UsuarioService;

@Path("/usuarios")
@Produces(value = {MediaType.APPLICATION_JSON})
public class UsuarioResource {

	@Inject
	private UsuarioService usuarioService;

	@GET
	@Path("/")
	public Response finddAll() {
		List<Usuario> retorno = usuarioService.fildAll();
		return retorno != null && !retorno.isEmpty() ? Response.ok(retorno).build() : Response.noContent().build();
	}

	@GET
	@Path("/find-by-id")
	public Response findById(@NotNull(message = "ID é obrigatório!") @QueryParam("id")Long id) {
		Usuario retorno = usuarioService.findById(id);
		return retorno != null ? Response.ok(retorno).build() : Response.noContent().build();
	}

	@POST
	@Path("/")
	public Response create(@Valid Usuario usuario) throws URISyntaxException {
		usuarioService.save(usuario);
		return Response.created(new URI("usuarios")).build();
	}

	@PUT
	@Path("/{id}")
	public Response edite(@PathParam("id") Long id,@Valid Usuario usuario) throws URISyntaxException, InvocationTargetException, IllegalAccessException {
		usuarioService.edit(id,usuario);
		return Response.created(new URI("usuarios")).build();
	}

	@DELETE
	@Path("/{id}")
	public Response remove(@NotNull(message = "ID é obrigatório!") @PathParam("id") Long id){
		usuarioService.remove(id);
		return Response.ok().build();
	}

	@PUT
	@Path("adiciona-perfil/{idPerfil}/usuario/{idUsuario}")
	public Response adicionaPerfilAUsuario(@NotNull(message = "ID Perfil é obrigatório!") @PathParam("idPerfil") Long idPerfil,
										   @NotNull(message = "ID Usuário é obrigatório!") @PathParam("idUsuario") Long idUsuario){

		Usuario u = usuarioService.adicionaPerfilAUsuario(idPerfil,idUsuario);
		return Response.ok(u).build();
	}

	@GET
	@Path("listar-perfis-usuario")
	public Response listarPerfisUsuario(@NotNull(message = "ID Usuário é obrigatório!") @QueryParam("idUsuario") Long idUsuario){
		List<Perfil> retorno = usuarioService.listarPerfisUsuario(idUsuario);
		return retorno != null && !retorno.isEmpty() ? Response.ok(retorno).build() : Response.noContent().build();
	}

	@PUT
	@Path("remove-perfil/{idPerfil}/usuario/{idUsuario}")
	public Response removePerfilUsuario(@NotNull(message = "ID Perfil é obrigatório!") @PathParam("idPerfil") Long idPerfil,
										   @NotNull(message = "ID Usuário é obrigatório!") @PathParam("idUsuario") Long idUsuario){

		Usuario u = usuarioService.removePerfilUsuario(idPerfil,idUsuario);
		return Response.ok(u).build();
	}

}
