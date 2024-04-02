package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.repository.PetRepository;

import jakarta.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PetServiceImpl implements PetService {

    @Inject
    private PetRepository petRepository; 

    @Override
    public List<PetDTO> buscarTodosPets() {
        List<Pet> pets = petRepository.buscarTodos(); 
        return pets.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO buscarPetPorNome(String nome) {
        Pet pet = petRepository.buscarPorNome(nome); 
        return converterParaDTO(pet);
    }

    @Override
    public PetDTO buscarPetPorId(Long id) {
        Pet pet = petRepository.buscarPorId(id); 
        return converterParaDTO(pet);
    }

    @Override
    @Transactional
    public Response criarPet(Pet pet) {
        petRepository.salvar(pet); 
        return Response.status(Response.Status.CREATED).build();
    }

    @Override
    @Transactional
    public Response atualizarPet(PetDTO petDTO, Long id) {
        Pet petExistente = petRepository.buscarPorId(id);
        if (petExistente != null) {
            petExistente.setNome(petDTO.nome()); 
           
            petRepository.atualizar(petExistente);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public Response deletarPet(Long id) {
        Pet pet = petRepository.buscarPorId(id);
        if (pet != null) {
            petRepository.remover(pet); 
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Collection<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.buscarTodos(); 
        return pets.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.buscarPorId(id); 
    }

    @Override
    @Transactional
    public void updatePet(Pet pet) {
        petRepository.atualizar(pet); 
    }

    private PetDTO converterParaDTO(Pet pet) {
        return new PetDTO(
            pet.getNome(),
            pet.getAnoNascimento(),
            pet.getTipoAnimal(),
            pet.getSexo()
        );
    }
}
