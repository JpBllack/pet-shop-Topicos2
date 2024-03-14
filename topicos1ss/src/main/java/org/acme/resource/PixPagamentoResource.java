package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.PixPagamentoDTO;
import org.acme.dto.PixPagamentoResponseDTO;
import org.acme.service.PixPagamentoService;

import java.util.List;

@Path("/pix")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PixPagamentoResource {
    @Inject
    PixPagamentoService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<PixPagamentoResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public PixPagamentoResponseDTO getId(@PathParam("id") long id){
        return service.getId(id);
    }

    @POST
    @RolesAllowed({"Admin", "User"})
    public Response insert(PixPagamentoDTO dto){
        return service.insert(dto);
    }

    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response update(@PathParam("id") long id, PixPagamentoDTO dto){
        return service.update(id, dto);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
