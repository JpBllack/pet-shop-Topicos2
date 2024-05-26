package br.projeto.petshop.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.projeto.petshop.dto.CompraResponseDTO;
import br.projeto.petshop.dto.ItemCompraResponseDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.service.CompraService;
import br.projeto.petshop.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

    @Inject
    private JsonWebToken jwt;

    @Inject
    private UsuarioService usuarioService;

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

    @GET
    @Path("/usuario/compras")
    @RolesAllowed({"User", "Admin", "Vet"})
    public Response getComprasUsuarioLogado() {
        try {


            String login = jwt.getSubject();
            UsuarioResponseDTO user = usuarioService.findByEmail(login);

            List<CompraResponseDTO> compras = compraService.getComprasByUserId(user.id());
            return Response.ok(compras).build();
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(Status.NOT_FOUND).entity(new Error("400", e.getMessage())).build();
        }
    }

    @POST
    @Path("/concluir")
    @RolesAllowed({"User", "Admin", "Vet"})
    public Response concluirCompra(List<ItemCompra> itensCompra) {
        try {

            String login = jwt.getSubject();
            UsuarioResponseDTO user = usuarioService.findByEmail(login);

            compraService.concluirCompra(itensCompra, user.id());
            return Response.status(Response.Status.OK).build();
        } catch(IllegalStateException e){
            LOG.error("Erro ao concluir compra", e);
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Erro ao concluir compra", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{compraId}/itens")
    @RolesAllowed({"User", "Admin", "Vet"})
    public Response getItensCompraByCompraId(@PathParam("compraId") Long compraId) {
        try {
            List<ItemCompra> itensCompra = compraService.getItensCompraByCompraId(compraId);
            List<ItemCompraResponseDTO> itensCompraResponse = itensCompra.stream()
                    .map(ItemCompraResponseDTO::valueOf)
                    .collect(Collectors.toList());
            return Response.ok(itensCompraResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao obter itens de compra").build();
        }
    }

}
