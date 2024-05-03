package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.repository.PetRepository;
import br.projeto.petshop.repository.TipoAnimalRepository;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PetServiceImpl implements PetService {

    @Inject
    private PetRepository petRepository;

    @Inject
    private TipoAnimalRepository tipoAnimalRepository;

    @Override
    public List<PetResponseDTO> buscarTodosPets() {
        List<Pet> pets = petRepository.listAll();
        if (pets.isEmpty()) {
            throw new NotFoundException("Nenhum pet encontrado");
        }
        return pets.stream().map(PetResponseDTO::valueOf).toList();
    }

    @Override
    public PetResponseDTO buscarPetPorId(Long id) {
        Pet pet = petRepository.findById(id);
        if (Objects.isNull(pet)) {
            throw new NotFoundException("Pet não encontrado");
        }
        return PetResponseDTO.valueOf(pet);
    }

    @Override
    public PetResponseDTO buscarPetPorNome(String nome) {
        Pet pet = petRepository.findByNome(nome);
        if (Objects.isNull(pet)) {
            throw new NotFoundException("Pet não encontrado");
        }
        return PetResponseDTO.valueOf(pet);
    }

    @Override
    public PetResponseDTO criarPet(PetDTO dto) {
        validarPetDTO(dto);
        
        Pet pet = new Pet();
        mapearPet(pet, dto);

        petRepository.persist(pet);
        return PetResponseDTO.valueOf(pet);
    }

    @Override
    public PetResponseDTO atualizarPet(Long id, PetDTO dto) {
        validarPetDTO(dto);

        Pet pet = petRepository.findById(id);
        if (Objects.isNull(pet)) {
            throw new NotFoundException("Pet não encontrado");
        }

        mapearPet(pet, dto);
        return PetResponseDTO.valueOf(pet);
    }

    @Override
    public void deletarPet(Long id) {
        if (!petRepository.deleteById(id)) {
            throw new NotFoundException("Pet não encontrado");
        }
    }

    private void validarPetDTO(PetDTO dto) {
        if (Objects.isNull(dto.anoNascimento())) {
            throw new ValidationException("400", "O ano de nascimento deve ser informado");
        }
        if (dto.nome().isBlank()) {
            throw new ValidationException("400", "O nome do pet deve ser informado");
        }
        if (Objects.isNull(dto.tipoAnimal())) {
            throw new ValidationException("400", "O tipo de animal deve ser informado");
        }
        if (Objects.isNull(dto.sexo())) {
            throw new ValidationException("400", "O sexo do animal deve ser informado");
        }
    }

    private void mapearPet(Pet pet, PetDTO dto) {
        pet.setNome(dto.nome());
        pet.setAnoNascimento(dto.anoNascimento());
        pet.setUsuario(dto.usuario());
        pet.setTipoAnimal(tipoAnimalRepository.findById(dto.tipoAnimal().getId()));
        pet.setSexo(dto.sexo());
    }
}
