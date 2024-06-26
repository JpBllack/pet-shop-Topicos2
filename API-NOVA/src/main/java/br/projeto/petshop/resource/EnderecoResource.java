package br.projeto.petshop.resource;

import org.jboss.logging.Logger;
import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.dto.EnderecoResponseDTO;
import br.projeto.petshop.service.EnderecoService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import br.projeto.petshop.application.Error;

import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    private static final Logger LOG = Logger.getLogger(EnderecoResource.class);

    @Inject
    EnderecoService enderecoService;

    @GET
    @PermitAll  
    public Response getAllEnderecos() {
        LOG.info("Buscando todos os endereços");
        List<EnderecoResponseDTO> enderecos = enderecoService.getAll();
        if (enderecos.isEmpty()) {
            LOG.info("Nenhum endereço encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum endereço encontrado").build();
        } else {
            LOG.info("Retornando todos os endereços");
            return Response.ok(enderecos).build();
        }
    }

    @GET
    @Path("/{id}")
    @PermitAll
    @Transactional
    public Response getEnderecoById(@PathParam("id") long id) {
        LOG.info("Buscando endereço pelo ID: " + id);
        try {
            EnderecoResponseDTO endereco = enderecoService.getId(id);
            LOG.info("Endereço encontrado: " + endereco);
            return Response.ok(endereco).build();
        } catch (NotFoundException e) {
            LOG.error("Endereço não encontrado", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/insert")
    @PermitAll
    @Transactional
    public Response insertEndereco(@Valid EnderecoDTO enderecoDTO, @QueryParam("userId") Long userId) {
        LOG.info("Inserindo novo endereço: " + enderecoDTO);
        try {
            return Response.status(Status.CREATED).entity(enderecoService.insert(enderecoDTO, userId)).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o endereço", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @PATCH
    @Path("/update/{id}")
    @PermitAll
    @Transactional
    public Response updateEndereco(@PathParam("id") long id, @Valid EnderecoDTO enderecoDTO) {
        LOG.info("Atualizando endereço com ID: " + id);
        try {
            enderecoService.update(id, enderecoDTO);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Endereço não encontrado para o ID: " + id, e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (ValidationException e) {
            LOG.error("Erro de validação ao atualizar o endereço com ID: " + id, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed({"Admin", "User", "Veterinario"})
    @Transactional
    public Response deleteEndereco(@PathParam("id") long id) {
        LOG.info("Deletando endereço pelo ID: " + id);
        try {
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Endereço não encontrado", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
