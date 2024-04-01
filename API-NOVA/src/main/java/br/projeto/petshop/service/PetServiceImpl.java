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
    private PetRepository petRepository; // Assumindo que você tem um repositório para interagir com o banco de dados

    @Override
    public List<PetDTO> buscarTodosPets() {
        List<Pet> pets = petRepository.buscarTodos(); // Supondo que seu repositório tenha um método para buscar todos os pets
        return pets.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO buscarPetPorNome(String nome) {
        Pet pet = petRepository.buscarPorNome(nome); // Supondo que seu repositório tenha um método para buscar pet por nome
        return converterParaDTO(pet);
    }

    @Override
    public PetDTO buscarPetPorId(Long id) {
        Pet pet = petRepository.buscarPorId(id); // Supondo que seu repositório tenha um método para buscar pet por ID
        return converterParaDTO(pet);
    }

    @Override
    @Transactional
    public Response criarPet(Pet pet) {
        petRepository.salvar(pet); // Supondo que seu repositório tenha um método para salvar um novo pet
        return Response.status(Response.Status.CREATED).build();
    }

    @Override
    @Transactional
    public Response atualizarPet(PetDTO petDTO, Long id) {
        Pet petExistente = petRepository.buscarPorId(id);
        if (petExistente != null) {
            petExistente.setNome(petDTO.nome()); // Atualize outros campos conforme necessário
            // Atualize o pet existente com os dados do DTO
            petRepository.atualizar(petExistente); // Supondo que seu repositório tenha um método para atualizar um pet
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
            petRepository.remover(pet); // Supondo que seu repositório tenha um método para remover um pet
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Collection<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.buscarTodos(); // Supondo que seu repositório tenha um método para buscar todos os pets
        return pets.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.buscarPorId(id); // Supondo que seu repositório tenha um método para buscar pet por ID
    }

    @Override
    @Transactional
    public void updatePet(Pet pet) {
        petRepository.atualizar(pet); // Supondo que seu repositório tenha um método para atualizar um pet
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
