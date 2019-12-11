package com.pauloneto.apirest.resources;

import com.pauloneto.apirest.models.Perfil;
import com.pauloneto.apirest.service.PerfilService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/perfil")
@Produces(value = {MediaType.APPLICATION_JSON})
public class PerfilResource {

    @Inject
    private PerfilService perfilService;

    @GET
    @Path("/")
    public Response findAll(){
        List<Perfil> retorno = perfilService.findAll();
        return retorno != null && !retorno.isEmpty() ? Response.ok(retorno).build() : Response.noContent().build();
    }

    @POST
    public Response create(@Valid() Perfil perfil) throws URISyntaxException {
        perfilService.save(perfil);
        return Response.created(new URI("perfil")).build();
    }

    @PUT
    @Path("/inativar")
    public Response inativar(@NotNull(message = "ID é obrigatório!") @QueryParam("id") Long id){
        Perfil retorno = perfilService.inativar(id);
        return Response.ok(retorno).build();
    }

    @PUT
    @Path("/ativar")
    public Response ativar(@NotNull(message = "ID é obrigatório!") @QueryParam("id") Long id){
        Perfil retorno = perfilService.ativar(id);
        return Response.ok(retorno).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@NotNull(message = "ID é obrigatório!") @PathParam("id") Long id){
        perfilService.remove(id);
        return Response.ok().build();
    }

}
