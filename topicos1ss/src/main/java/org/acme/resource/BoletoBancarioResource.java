package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.BoletoBancarioDTO;
import org.acme.dto.BoletoBancarioResponseDTO;
import org.acme.service.BoletoBancarioService;

import java.util.List;

@Path("/boleto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoletoBancarioResource {
    @Inject
    BoletoBancarioService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<BoletoBancarioResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public BoletoBancarioResponseDTO getId(@PathParam("id") long id){
        return service.getId(id);
    }

    @POST
    @RolesAllowed({"Admin", "User"})
    public Response insert(BoletoBancarioDTO BoletoBancarioDTO){
        return service.insert(BoletoBancarioDTO);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        return service.delete(id);
    }
}
