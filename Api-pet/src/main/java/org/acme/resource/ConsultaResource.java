package org.acme.resource;

import org.acme.dto.ConsultaDTO;
import org.acme.service.ConsultaService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/consulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaService consultaService;

    @POST
    @PermitAll
    public Response criarConsulta(ConsultaDTO consultaDTO) {
        return consultaService.criarConsulta(consultaDTO);
    }

    @PUT
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response atualizarConsulta(ConsultaDTO consultaDTO, @PathParam("id") long id) {
        return consultaService.atualizarConsulta(consultaDTO, id);
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response buscarConsultaPorId(@PathParam("id") long id) {
        return consultaService.buscarConsultaPorId(id);
    }

    @GET
    @PermitAll
    public List<ConsultaDTO> buscarTodasConsultas() {
        return consultaService.buscarTodasConsultas();
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response deletarConsulta(@PathParam("id") long id) {
        return consultaService.deletarConsulta(id);
    }
}
