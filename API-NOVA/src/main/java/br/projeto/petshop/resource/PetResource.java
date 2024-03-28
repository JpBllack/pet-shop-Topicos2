package br.projeto.petshop.resource;

import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.service.PetService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @Inject
    PetService petService;

    /* @GET
    @Path("/")
    public Response getAllPets() {
        List<PetDTO> petDTOList = petService.getAllPets().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return Response.ok(petDTOList).build();
    }*/

    @GET
    @Path("/{id}")
    public Response getPetById(@PathParam("id") Long id) {
        PetDTO petDTO = mapToDTO(petService.getPetById(id));
        return Response.ok(petDTO).build();
    }

    @POST
    @Transactional
    @Path("/")
    public Response createPet(PetDTO petDTO) {
        Pet pet = mapToEntity(petDTO);
        petService.criarPet(pet);
        return Response.ok().build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response updatePet(@PathParam("id") Long id, PetDTO petDTO) {
        Pet pet = mapToEntity(petDTO);
        pet.setId(id);
        petService.updatePet(pet);
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deletePet(@PathParam("id") Long id) {
        petService.deletarPet(id);
        return Response.ok().build();
    }

    // Métodos utilitários para mapear DTOs para entidades e vice-versa

    private PetDTO mapToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setNome(pet.getNome());
        petDTO.setAnoNascimento(pet.getAnoNascimento());
        petDTO.setTipoAnimal(pet.getTipoAnimal());
        petDTO.setSexo(pet.getSexo());
        return petDTO;
    }

    private Pet mapToEntity(PetDTO petDTO) {
        return new Pet(
                petDTO.getId(),
                petDTO.getNome(),
                petDTO.getAnoNascimento(),
                petDTO.getTipoAnimal(),
                petDTO.getSexo()
        );
    }
}
