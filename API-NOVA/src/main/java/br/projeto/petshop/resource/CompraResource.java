package br.projeto.petshop.resource;

import java.util.List;

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

    @POST
    @Path("/concluir")
    public Response concluirCompra(List<ItemCompra> itensCompra) {
        compraService.concluirCompra(itensCompra);
        return Response.status(Response.Status.OK).build();
    }
    
}
