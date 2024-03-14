package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.*;
import org.acme.dto.VendaDTO;
import org.acme.service.VendaService;

import java.util.List;

@Path("/venda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendaResource {
    @Inject
    VendaService service;

    @GET
    @RolesAllowed({"Admin"})
    public Response getAll(){
        return service.getAll();
    }
    @GET
    @RolesAllowed({"Admin", "User"})
    @Path("/{id}")
    public VendaResponseCompDTO getId(@PathParam("id") long id){
        return service.getId(id);
    }

    @PATCH
    @RolesAllowed({"Admin", "User"})
    @Path("/pagamento")
    public Response insertPagamento(VendaPagamentoDTO vendaPagamentoDTO) {
        return service.insertPagamento(vendaPagamentoDTO);
    }

    @POST
    @RolesAllowed({"Admin", "User"})
    public Response insert(VendaDTO dto) {
        return service.insert(dto);
    }

    @POST
    @Path("/comp")
    @RolesAllowed({"Admin", "User"})
    public Response insertComp(VendaCompDTO dto){
        return service.insertComp(dto);
    }

    @POST
    @RolesAllowed({"Admin", "User"})
    @Path("/avaliacao/insert")
    public Response insertAvaliacao(AvaliacaoProdutoVendaDTO dto) {
        return service.adicionaAvaliacao(dto);
    }

    @PATCH
    @RolesAllowed({"Admin", "User"})
    @Path("/avaliacao/insertid")
    public Response adicionaAvaliacaoId(AvaliacaoProdutoVendaIdDTO dto) {
        return service.adicionaAvaliacaoId(dto);
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
