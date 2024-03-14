package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AvaliacaoDTO;
import org.acme.dto.AvaliacaoResponseDTO;
import org.acme.service.AvaliacaoService;

import java.util.List;

@Path("/avaliacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {
    @Inject
    AvaliacaoService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<AvaliacaoResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public AvaliacaoResponseDTO getId(@PathParam("id") long id){
        return service.getId(id);
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(AvaliacaoDTO avaliacaoDTO){
        return service.insert(avaliacaoDTO);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        return service.delete(id);
    }
}
