package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CartaoCreditoDTO;
import org.acme.dto.CartaoCreditoResponseDTO;
import org.acme.service.CartaoCreditoService;

import java.util.List;

@Path("/cartao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoCreditoResource {
    @Inject
    CartaoCreditoService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<CartaoCreditoResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public CartaoCreditoResponseDTO getId(@PathParam("id") Long id){
        return service.getId(id);
    }

    @POST
    @RolesAllowed({"Admin", "User"})
    public Response insert(CartaoCreditoDTO CartaoCreditoDTO){
        return service.insert(CartaoCreditoDTO);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        return service.delete(id);
    }
}
