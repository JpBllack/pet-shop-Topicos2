package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import br.projeto.petshop.validation.ValidationException;
import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Usuario;
import br.projeto.petshop.repository.PetRepository;
import br.projeto.petshop.repository.TipoAnimalRepository;
import br.projeto.petshop.repository.UsuarioRepository;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class PetServiceImpl implements PetService {

    @Inject
    private PetRepository petRepository;

    @Inject
    private TipoAnimalRepository tipoAnimalRepository;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Override
    public PetResponseDTO criarPet(PetDTO dto) {
        validarPetDTO(dto);
        
        Pet pet = new Pet();

        pet.setNome(dto.nome());
        pet.setAnoNascimento(dto.anoNascimento());
        pet.setTipoAnimal(tipoAnimalRepository.findById(dto.tipoAnimal().getId()));
        pet.setSexo(dto.sexo());

        // Define o Usuario associado ao Pet
        Usuario usuario = usuarioRepository.findById(dto.usuarioId());
        if (usuario == null) {
            throw new NotFoundException("Usuario não encontrado");
        }
        pet.setUsuario(usuario);

        petRepository.persist(pet);

        return PetResponseDTO.valueof(pet);
    }

    @Override
    public PetResponseDTO atualizarPet(Long id, PetDTO dto) {
        validarPetDTO(dto);
        
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            throw new NotFoundException("Pet não encontrado");
        }

        pet.setNome(dto.nome());
        pet.setAnoNascimento(dto.anoNascimento());
        pet.setTipoAnimal(tipoAnimalRepository.findById(dto.tipoAnimal().getId()));
        pet.setSexo(dto.sexo());

        // Atualiza o Usuario associado ao Pet
        Usuario usuario = usuarioRepository.findById(dto.usuarioId());
        if (usuario == null) {
            throw new NotFoundException("Usuario não encontrado");
        }
        pet.setUsuario(usuario);

        petRepository.persist(pet);

        return PetResponseDTO.valueof(pet);
    }

    private void validarPetDTO(PetDTO dto) {
        if (dto.anoNascimento() == null) {
            throw new ValidationException("400", "O ano de nascimento deve ser informado");
        } else if (dto.nome().isBlank() || dto.nome().isEmpty()) {
            throw new ValidationException("400", "O nome do pet deve ser informado");
        } else if (dto.tipoAnimal() == null) {
            throw new ValidationException("400", "O tipo de animal deve ser informado");
        } else if (dto.sexo() == null) {
            throw new ValidationException("400", "O sexo do animal deve ser informado");
        } else if (dto.usuarioId() == null) {
            throw new ValidationException("400", "O ID do usuário associado deve ser informado");
        }
    }

    @Override
    public void deletarPet(Long id) {
        if (!petRepository.deleteById(id)) {
            throw new NotFoundException("Pet não encontrado");
        }
    }

    @Override
    public List<PetResponseDTO> buscarTodosPets() {
        if (petRepository.listAll().stream().map(PetResponseDTO::valueof).toList().isEmpty()) {
            throw new NotFoundException("Pet não encontrado");
        }
        return petRepository.listAll().stream().map(PetResponseDTO::valueof).toList();
    }

    @Override
    public PetResponseDTO buscarPetPorNome(String nome) {
        Pet pet = petRepository.findByNome(nome);
        if (pet == null) {
            throw new NotFoundException("Pet não encontrado");
        }
        return PetResponseDTO.valueof(pet);
    }

    @Override
    public PetResponseDTO buscarPetPorId(Long id) {
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            throw new NotFoundException("Pet não encontrado");
        }
        return PetResponseDTO.valueof(pet);
    }
}
