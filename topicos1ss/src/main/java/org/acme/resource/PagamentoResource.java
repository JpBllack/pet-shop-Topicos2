package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.PagamentoDTO;
import org.acme.dto.PagamentoResponseDTO;
import org.acme.service.PagamentoService;

import java.util.List;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {
    @Inject
    PagamentoService service;

    @GET
    @RolesAllowed({"Admin"})
    public List<PagamentoResponseDTO> getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public PagamentoResponseDTO getId(@PathParam("id") long id){
        return service.getId(id);
    }
    @GET
    @RolesAllowed({"Admin"})
    @Path("/tipo/{tipopagamento}")
    public List<PagamentoResponseDTO> getTipo(@PathParam("tipopagamento") String tipopagamento){
        return service.getByTipo(tipopagamento);
    }

    @POST
    @RolesAllowed({"Admin", "User"})
    public Response insert(PagamentoDTO categoriaDTO){
        return service.insert(categoriaDTO);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
