package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.EstadoDTO;
import org.acme.dto.EstadoResponseDTO;
import org.acme.service.EstadoService;

import java.util.List;

@Path("/estado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {
    @Inject
    EstadoService service;

    @GET
    @PermitAll
    public List<EstadoResponseDTO> getAll(){
        return service.findAll();
    }
    @GET
    @PermitAll
    @Path("/{id}")
    public EstadoResponseDTO getId(@PathParam("id") long id){
        return service.findById(id);
    }
    @GET
    @PermitAll
    @Path("/{nome}")
    public List<EstadoResponseDTO> getNome(@PathParam("nome") String nome){
        return service.findByNome(nome);
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(EstadoDTO dto){
        return service.insert(dto);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("{id}")
    public EstadoResponseDTO update(@PathParam("id") long id, EstadoDTO dto){
        return service.update(dto, id);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
