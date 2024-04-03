package br.projeto.petshop.resource;

import br.projeto.petshop.dto.ConsultaDTO;
import br.projeto.petshop.dto.ConsultaResponseDTO;
import br.projeto.petshop.service.ConsultaService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.projeto.petshop.application.Error;

import java.util.List;

import org.jboss.logging.Logger;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaService consultaService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Transactional
    //@PermitAll
    public Response criarConsulta(ConsultaDTO consultaDTO) {
        try{
            return Response.status(Status.CREATED).entity(consultaService.criarConsulta(consultaDTO)).build();
        } catch (ValidationException e){
            LOG.error("Erro ao criar consulta");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch (NotFoundException e){
            LOG.error("Veterinario ou Pet não encontrados");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @PUT
    @Transactional
    //@RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response atualizarConsulta(@PathParam("id") long id, ConsultaDTO consultaDTO) {
        try{
            LOG.info("Atualizando consulta");
            consultaService.atualizarConsulta(id, consultaDTO);
            return Response.noContent().build();
        } catch (NotFoundException e){
            LOG.error("Não foi possivel atualizar a consulta");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        } 
    }

    @GET
    //@PermitAll
    @Path("/{id}")
    public Response buscarConsultaPorId(@PathParam("id") long id, ConsultaDTO dto) {
        try {
            LOG.infof("Update em consulta");
            consultaService.atualizarConsulta(id, dto);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Update não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    //@PermitAll
    public Response buscarTodasConsultas() {
        try{
            LOG.info("Buscando todas as consultas");
            ConsultaResponseDTO[] consultas = consultaService.buscarTodasConsultas().toArray(new ConsultaResponseDTO[0]);
            if(consultas.length == 0){
                LOG.info("nenhuma consulta encontrada");
                return Response.status(Status.NOT_FOUND).entity("Nenhum tipo encontrado").build();
            } else {
                LOG.info("Retornando todas as consultas");
                return Response.ok(consultas).build();
            } 
        } catch (NotFoundException e) {
            LOG.error("consultas não encontradas");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    //@RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response deletarConsulta(@PathParam("id") long id) {
        try {
            LOG.info("Deletando consulta");
            consultaService.deletarConsulta(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Deleção não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }
}
