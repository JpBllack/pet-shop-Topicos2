package br.projeto.petshop.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.service.CompraService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/compra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompraResource {

    @Inject
    private CompraService compraService;

    private static final Logger LOG = Logger.getLogger(PetResource.class);

    @POST
    @Path("/concluir")
    public Response concluirCompra(List<ItemCompra> itensCompra) {
        try {
            compraService.concluirCompra(itensCompra);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            LOG.error("Erro ao concluir compra", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    
}
