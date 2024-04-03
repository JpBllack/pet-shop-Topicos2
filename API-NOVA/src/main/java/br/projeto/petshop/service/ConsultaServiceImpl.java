package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.ConsultaDTO;
import br.projeto.petshop.dto.ConsultaResponseDTO;
import br.projeto.petshop.dto.TipoAnimalDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.model.Consulta;
import br.projeto.petshop.model.Usuario;
import br.projeto.petshop.repository.ConsultaRepository;
import br.projeto.petshop.repository.PetRepository;
import br.projeto.petshop.repository.UsuarioRepository;
import br.projeto.petshop.validation.ValidationException;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ConsultaServiceImpl implements ConsultaService{

    @Inject
    ConsultaRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PetRepository petRepository;

    @Override
    public List<ConsultaResponseDTO> buscarTodasConsultas() {
        if(repository.listAll().stream().map(e -> ConsultaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há consultas");
        }
        return repository.listAll().stream().map(e -> ConsultaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public ConsultaResponseDTO criarConsulta(ConsultaDTO consultaDTO) {
        if(consultaDTO.pet() == null){
            throw new ValidationException("400", "O pet deve ser informado");
        } else if(consultaDTO.veterinario() == null){
            throw new ValidationException("400", "O veterinario deve ser informao");
        } else if (consultaDTO.data() == null) {
            throw new ValidationException("400", "A data deve ser informada");
        }

        if(usuarioRepository.findById(consultaDTO.veterinario()) == null){
            throw new NotFoundException("Esse veterinario não existe");
        } else if(petRepository.findById(consultaDTO.pet()) == null){
            throw new NotFoundException("Esse pet não existe");
        }

        Consulta consulta = new Consulta();

        consulta.setPet(petRepository.findById(consultaDTO.pet()));
        consulta.setVeterinario(usuarioRepository.findById(consultaDTO.veterinario()));
        consulta.setData(consultaDTO.data());
        consulta.setMotivo(consultaDTO.motivo());

        Usuario veterinario = usuarioRepository.findById(consultaDTO.veterinario());
        if(veterinario.getPerfil().getId() != 3){
            throw new ForbiddenException("Um veterinario deve ser informado");
        }

        repository.persist(consulta);;

        return ConsultaResponseDTO.valueOf(consulta);

    }

    @Override
    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaDTO consultaDTO) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Consulta não encontrada");
        }

        if(consultaDTO.pet() == null){
            throw new ValidationException("400", "O pet deve ser informado");
        } else if(consultaDTO.veterinario() == null){
            throw new ValidationException("400", "O veterinario deve ser informao");
        } else if (consultaDTO.data() == null) {
            throw new ValidationException("400", "A data deve ser informada");
        }

        if(usuarioRepository.findById(consultaDTO.veterinario()) == null){
            throw new NotFoundException("Esse veterinario não existe");
        } else if(petRepository.findById(consultaDTO.pet()) == null){
            throw new NotFoundException("Esse pet não existe");
        }

        Consulta consulta = repository.findById(id);

        consulta.setPet(petRepository.findById(consultaDTO.pet()));
        consulta.setVeterinario(usuarioRepository.findById(consultaDTO.veterinario()));
        consulta.setData(consultaDTO.data());
        consulta.setMotivo(consultaDTO.motivo());

        Usuario veterinario = usuarioRepository.findById(consultaDTO.veterinario());
        if(veterinario.getPerfil().getId() != 3){
            throw new ForbiddenException("Um veterinario deve ser informado");
        }

        return ConsultaResponseDTO.valueOf(consulta);
    }

    @Override
    public ConsultaResponseDTO buscarConsultaPorId(long id) {
        if(repository.findById(id) == null) {
            throw new NotFoundException("Consulta não encontrada");
        }
        return ConsultaResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public void deletarConsulta(long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Consulta não encontrada");
        }
    }
    
}
