package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import br.projeto.petshop.validation.ValidationException;
import jakarta.transaction.Transactional;
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.repository.PetRepository;
import br.projeto.petshop.repository.TipoAnimalRepository;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PetServiceImpl implements PetService {

    @Inject
    private PetRepository petRepository;

    @Inject
    TipoAnimalRepository tipoAnimalRepository;

    @Override
    public PetResponseDTO criarPet(PetDTO dto) {
        if(dto.anoNascimento() == null){
            throw new ValidationException("400", "O ano de nascimento deve ser informado");
        } else if(dto.nome().isBlank() || dto.nome().isEmpty()){
            throw new ValidationException("400", "O nome do pet deve ser informado");
        } else if(dto.tipoAnimal() == null){
            throw new ValidationException("400", "O tipo de animal deve ser informado");
        } else if(dto.sexo() == null){
            throw new ValidationException("400", "O sexo do animal deve ser informado");
        }

        Pet pet = new Pet();

        pet.setNome(dto.nome());
        pet.setAnoNascimento(dto.anoNascimento());
        pet.setTipoAnimal(tipoAnimalRepository.findById(dto.tipoAnimal().getId()));
        pet.setSexo(dto.sexo());

        petRepository.persist(pet);

        return PetResponseDTO.valueof(pet);

    }

    @Override
    public PetResponseDTO atualizarPet(Long id, PetDTO dto) {
        if(dto.anoNascimento() == null){
            throw new ValidationException("400", "O ano de nascimento deve ser informado");
        } else if(dto.nome().isBlank() || dto.nome().isEmpty()){
            throw new ValidationException("400", "O nome do pet deve ser informado");
        } else if(dto.tipoAnimal() == null){
            throw new ValidationException("400", "O tipo de animal deve ser informado");
        } else if(dto.sexo() == null){
            throw new ValidationException("400", "O sexo do animal deve ser informado");
        } else if(petRepository.findById(id) == null){
            throw new NotFoundException("Pet não encontrado");
        }

        Pet pet = petRepository.findById(id);

        pet.setNome(dto.nome());
        pet.setAnoNascimento(dto.anoNascimento());
        pet.setTipoAnimal(tipoAnimalRepository.findById(dto.tipoAnimal().getId()));
        pet.setSexo(dto.sexo());

        return PetResponseDTO.valueof(pet);
    }

    @Override
    public void deletarPet(Long id) {
        if(!petRepository.deleteById(id)){
            throw new NotFoundException("Pet não encontrado");
        }
    }

    @Override
    public List<PetResponseDTO> buscarTodosPets() {
        if(petRepository.listAll().stream().map(e -> PetResponseDTO.valueof(e)).toList().isEmpty()){
            throw new NotFoundException("Pet não encontrado");
        }
        return petRepository.listAll().stream().map(e -> PetResponseDTO.valueof(e)).toList();
    }

    @Override
    public PetResponseDTO buscarPetPorNome(String nome) {
        if(petRepository.findByNome(nome) == null) {
            throw new NotFoundException("Pet não encontrado");
        }
        return PetResponseDTO.valueof(petRepository.findByNome(nome));
    }

    @Override
    public PetResponseDTO buscarPetPorId(Long id) {
        if(petRepository.findById(id) == null){
            throw new NotFoundException("Pet não encontrado");
        }
        return PetResponseDTO.valueof(petRepository.findById(id));
    } 

    
}
