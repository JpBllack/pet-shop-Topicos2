package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.TelefoneDTO;
import org.acme.dto.TelefoneResponseDTO;
import org.acme.service.TelefoneService;

import java.util.List;

@Path("/telefone")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {
    @Inject
    TelefoneService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<TelefoneResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public TelefoneResponseDTO getId(@PathParam("id") long id){
        return service.getId(id);
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/codigo/{codigo}")
    public List<TelefoneResponseDTO> getCodigoArea(@PathParam("codigo") String codigo){
        return service.getCodigoArea(codigo);
    }

    @POST
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response insert(@PathParam("id") String id, TelefoneDTO dto){
        return service.insert(dto, id);
    }

    @PUT
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response update(@PathParam("id") long id, TelefoneDTO dto){
        return service.update(id, dto);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
