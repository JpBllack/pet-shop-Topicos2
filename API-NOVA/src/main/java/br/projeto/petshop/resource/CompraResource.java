package br.projeto.petshop.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.CompraResponseDTO;
import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.service.CompraService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.application.Error;

@Path("/compra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompraResource {

    @Inject
    private CompraService compraService;

    private static final Logger LOG = Logger.getLogger(PetResource.class);

    @GET
    @RolesAllowed({"Admin"})
    public Response findAll(){
        try{
            LOG.info("Buscando todas as compras");
            CompraResponseDTO[] compras = compraService.getAllCompras().toArray(new CompraResponseDTO[0]);
            return Response.ok(compras).build();
        } catch(Exception e){
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @POST
    @Path("/concluir")
    @RolesAllowed({"User", "Admin", "Vet"})
    public Response concluirCompra(List<ItemCompra> itensCompra) {
        try {
            compraService.concluirCompra(itensCompra);
            return Response.status(Response.Status.OK).build();
        } catch(IllegalStateException e){
            LOG.error("Erro ao concluir compra", e);
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Erro ao concluir compra", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
