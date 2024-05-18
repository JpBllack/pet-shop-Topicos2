package br.projeto.petshop.resource;

import org.jboss.logging.Logger;

import br.projeto.petshop.service.PetService;
import br.projeto.petshop.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
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
import br.projeto.petshop.application.Error;
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;

import java.util.List;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @Inject
    PetService petService;

    private static final Logger LOG = Logger.getLogger(PetResource.class);

    @GET
    @Path("/all")
    @RolesAllowed({"Admin", "User", "Veterinario"})
    public Response findAll(){
        try{
            List<PetResponseDTO> pets = petService.getAll();
            return Response.ok(pets).build();
        } catch (NotFoundException e){
            LOG.error("Pets não encontrados");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin", "User", "Veterinario"})
    public Response findById(@PathParam("id") Long id){
        try{
            PetResponseDTO pet = petService.getById(id);
            return Response.ok(pet).build();
        } catch (NotFoundException e){
            LOG.error("Pet não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @POST
    @Transactional
    @Path("/insert")
    @RolesAllowed("Admin")
    public Response insert(PetDTO dto){
        try{
            return Response.status(Response.Status.CREATED).entity(petService.insert(dto)).build();
        } catch (ValidationException e){
            LOG.error("Erro ao criar PET");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        } catch (NotFoundException e){
            LOG.error("Pet não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/update/{id}")
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, PetDTO dto){
        try{
            return Response.ok(petService.update(id, dto)).build();
        } catch (ValidationException e){
            LOG.error("Erro ao atualizar pet");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        } catch (NotFoundException e){
            LOG.error("Pet não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    @RolesAllowed("Admin")
    public Response delete(@PathParam("id") Long id){
        try{
            petService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e){
            LOG.error("Pet não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Transactional
    @Path("/byName/{nome}")
    @RolesAllowed({"Admin", "User", "Veterinario"})
    public Response findByName(@PathParam("nome") String nome){
        try{
            PetResponseDTO pet = petService.getByNome(nome);
            return Response.ok(pet).build();
        } catch (NotFoundException e){
            LOG.error("Pet não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }
}
