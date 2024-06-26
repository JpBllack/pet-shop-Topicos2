package br.projeto.petshop.resource;

import br.projeto.petshop.dto.MunicipioDTO;
import br.projeto.petshop.dto.MunicipioResponseDTO;
import br.projeto.petshop.service.MunicipioService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.application.Error;

import org.jboss.logging.Logger;

import java.util.List;

@Path("/municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    private static final Logger LOG = Logger.getLogger(MunicipioResource.class);

    @Inject
    MunicipioService municipioService;

    @GET
    @PermitAll
    public Response getAllMunicipios() {
        LOG.info("Buscando todos os municípios");
        List<MunicipioResponseDTO> municipios = municipioService.getAll();
        if (municipios.isEmpty()) {
            LOG.info("Nenhum município encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum município encontrado").build();
        } else {
            LOG.info("Retornando todos os municípios");
            return Response.ok(municipios).build();
        }
    }

    @GET
    @PermitAll
    @Path("/search/estado/{id}")
    public Response getMunicipioByIdEstado(@PathParam("id") Long id){
        try{
            return Response.ok(municipioService.getByEstadoId(id)).build();
        } catch(Exception e){
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }


    @GET
    @PermitAll
    @Path("/{id}")
    @Transactional
    public Response getMunicipioById(@PathParam("id") long id) {
        LOG.info("Buscando município pelo ID: " + id);
        try {
            MunicipioResponseDTO municipio = municipioService.getById(id);
            LOG.info("Município encontrado: " + municipio);
            return Response.ok(municipio).build();
        } catch (NotFoundException e) {
            LOG.error("Município não encontrado", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @RolesAllowed({"Admin"})
    @Path("/insert")
    @Transactional
    public Response insertMunicipio(@Valid MunicipioDTO municipioDTO) {
        LOG.info("Inserindo novo município: " + municipioDTO);
        municipioService.insertMunicipio(municipioDTO);
        LOG.info("Município inserido com sucesso");
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @RolesAllowed({"Admin"})
    @Path("/update/{id}")
    @Transactional
    public Response updateMunicipio(@PathParam("id") long id, @Valid MunicipioDTO municipioDTO) {
        LOG.info("Atualizando município com ID: " + id);
        try {
            municipioService.updateMunicipio(id, municipioDTO);
            LOG.info("Município atualizado com sucesso");
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Município não encontrado para o ID: " + id, e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (ValidationException e) {
            LOG.error("Erro de validação ao atualizar o município com ID: " + id, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("/delete/{id}")
    @Transactional
    public Response deleteMunicipio(@PathParam("id") long id) {
        LOG.info("Deletando município pelo ID: " + id);
        try {
            municipioService.deleteMunicipio(id);
            LOG.info("Município deletado com sucesso");
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Município não encontrado", e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
