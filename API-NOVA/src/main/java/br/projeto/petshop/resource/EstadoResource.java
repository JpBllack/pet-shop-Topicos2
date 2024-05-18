package br.projeto.petshop.resource;

import br.projeto.petshop.dto.EstadoDTO;
import br.projeto.petshop.service.EstadoService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    @Inject
    EstadoService estadoService;

    @GET
    @RolesAllowed({"Admin", "User", "Veterinario"})
    @Transactional
    public Response getAllEstados() {
        LOG.info("Buscando todos os estados");
        List<EstadoDTO> estados = estadoService.getAll();
        if (estados.isEmpty()) {
            LOG.info("Nenhum estado encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum estado encontrado").build();
        } else {
            LOG.info("Retornando todos os estados");
            return Response.ok(estados).build();
        }
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    @Transactional
    public Response getEstadoById(@PathParam("id") long id) {
        LOG.info("Buscando estado pelo ID: " + id);
        try {
            EstadoDTO estado = estadoService.getById(id);
            LOG.info("Estado encontrado: " + estado);
            return Response.ok(estado).build();
        } catch (NotFoundException e) {
            LOG.error("Estado não encontrado", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @RolesAllowed({"Admin"})
    @Path("/insert")
    @Transactional
    public Response insertEstado(@Valid EstadoDTO estadoDTO) {
        LOG.info("Inserindo novo estado: " + estadoDTO);
        try {
            estadoService.insert(estadoDTO);
            LOG.info("Estado inserido com sucesso");
            return Response.status(Response.Status.CREATED).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o estado", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @RolesAllowed({"Admin"})
    @Path("/update/{id}")
    @Transactional
    public Response updateEstado(@PathParam("id") long id, @Valid EstadoDTO estadoDTO) {
        LOG.info("Atualizando estado com ID: " + id);
        try {
            estadoService.update(id, estadoDTO);
            LOG.info("Estado atualizado com sucesso");
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Estado não encontrado para o ID: " + id, e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (ValidationException e) {
            LOG.error("Erro de validação ao atualizar o estado com ID: " + id, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/delete/{id}")
    @Transactional
    public Response deleteEstado(@PathParam("id") long id) {
        LOG.info("Deletando estado pelo ID: " + id);
        try {
            estadoService.delete(id);
            LOG.info("Estado deletado com sucesso");
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Estado não encontrado para o ID: " + id, e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
