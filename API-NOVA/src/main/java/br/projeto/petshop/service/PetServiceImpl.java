package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.repository.PetRepository;
import br.projeto.petshop.service.PetService;

import jakarta.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PetServiceImpl implements PetService {

    @Inject
    PetRepository petRepository;

    @Override
    public List<PetDTO> buscarTodosPets() {
        return petRepository.listAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO buscarPetPorNome(String nome) {
        Pet pet = petRepository.findByNome(nome);
        return mapToDTO(pet);
    }

    @Override
    public PetDTO buscarPetPorId(Long id) {
        Pet pet = petRepository.findById(id);
        return mapToDTO(pet);
    }

    @Override
    @Transactional
    public Response criarPet(Pet pet) {
        petRepository.persist(pet);
        return Response.ok().build();
    }

    @Override
    @Transactional
    public Response atualizarPet(PetDTO petDTO, Long id) {
        Pet pet = mapToEntity(petDTO);
        pet.setId(id);
        petRepository.update(pet);
        return Response.ok().build();
    }

    @Override
    @Transactional
    public Response deletarPet(Long id) {
        petRepository.deleteById(id);
        return Response.ok().build();
    }

    @Override
    public Collection<PetDTO> getAllPets() {
        return buscarTodosPets();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    @Transactional
    public void updatePet(Pet pet) {
        petRepository.update(pet);
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
