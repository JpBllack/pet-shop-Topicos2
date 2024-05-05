package br.projeto.petshop.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.MarcaDTO;
import br.projeto.petshop.dto.MarcaResponseDTO;
import br.projeto.petshop.dto.TipoAnimalDTO;
import br.projeto.petshop.model.Marca;
import br.projeto.petshop.service.MarcaService;
import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.application.Error;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {
    
    @Inject
    MarcaService marcaService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);


    @GET
    @Path("/all")
    public Response findAll(){
        try{
            List<MarcaResponseDTO> marcas = marcaService.getAll();
            return Response.ok(marcas).build();
        } catch (NotFoundException e){
            LOG.error("Marcas não encontradas");;
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando marca de id %s", id);
            return Response.ok(marcaService.getById(id)).build();
        } catch(NotFoundException e) {
            LOG.error("Marca não encontrada");
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
            return Response.ok(marcaService.getByNome(nome)).build();
        } catch(NotFoundException e) {
            LOG.error("Marca não encontrada");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(MarcaDTO dto){
        try{
            return Response.status(Response.Status.CREATED).entity(marcaService.insert(dto)).build();
        }
        catch(ValidationException e){
            LOG.error("Erro ao registrar marca");
            e.printStackTrace();;
            Error error = new Error(e.getFieldName() ,e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        } catch (NotFoundException e){
            LOG.error("Marca não encontrada");;
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/update/{id}")
    public Response update(@PathParam("id") Long id, MarcaDTO dto){
        try{
            LOG.infof("Update em %s", dto.nome());
            marcaService.update(id, dto);
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
            marcaService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Deleção não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }



}
