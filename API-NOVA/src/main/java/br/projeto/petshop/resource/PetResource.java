package br.projeto.petshop.resource;

import org.jboss.logging.Logger;

import br.projeto.petshop.service.PetService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.inject.Inject;
import jakarta.transaction.Status;
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
import br.projeto.petshop.dto.PetDTO;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @Inject
    PetService petService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Transactional
    @Path("/create")
    public Response insert(PetDTO dto){
        try{
            return Response.status(Status.CREATED).entity(petService.criarPet(dto.build);
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
    
}
