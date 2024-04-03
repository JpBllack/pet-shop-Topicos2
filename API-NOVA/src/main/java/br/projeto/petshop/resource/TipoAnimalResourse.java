package br.projeto.petshop.resource;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.TipoAnimalDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.service.TipoAnimalService;
import br.projeto.petshop.validation.ValidationException;
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

@Path("/tipos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoAnimalResourse {

    @Inject
    TipoAnimalService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(TipoAnimalDTO dto) {
        try {
            LOG.info("Inserindo um tipo");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao inserir o tipo");
            e.printStackTrace();
            ;
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/update/{id}")
    public Response update(@PathParam("id") Long id, TipoAnimalDTO dto) {
        try {
            LOG.infof("Update em %s", dto.nome());
            service.update(id, dto);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Update não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            LOG.info("Deletando tipo");
            service.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Deleção não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    public Response findAll() {
        try {
            LOG.info("Buscando todos os tipos");
            TipoAnimalDTO[] tipos = service.getAll().toArray(new TipoAnimalDTO[0]);
            if (tipos.length == 0) {
                LOG.info("Nenhum usuário encontrado");
                return Response.status(Status.NOT_FOUND).entity("Nenhum tipo encontrado").build();
            } else {
                LOG.info("Retornando todos os tipos");
                return Response.ok(tipos).build();
            }
        } catch (NotFoundException e) {
            LOG.error("tipos não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando tipo de id %s", id);
            return Response.ok(service.findById(id)).build();
        } catch(NotFoundException e) {
            LOG.error("Tipo não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        try{
            LOG.infof("Buscando %s", nome);
            return Response.ok(service.findByNome(nome)).build();
        } catch(NotFoundException e) {
            LOG.error("Tipo não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

}
