package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.MarcaDTO;
import br.projeto.petshop.dto.MarcaResponseDTO;
import br.projeto.petshop.model.Marca;
import br.projeto.petshop.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import br.projeto.petshop.validation.ValidationException;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService{

    @Inject
    private MarcaRepository marcaRepository;

    @Override
    public List<MarcaResponseDTO> getAll(){
        if(marcaRepository.listAll().stream().map(e -> MarcaResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há marcas");
        }
        return marcaRepository.listAll().stream().map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public MarcaResponseDTO insert(MarcaDTO dto) {
        if(dto.nome().isBlank() || dto.nome().isEmpty()){
            throw new ValidationException("nome", "O nome deve ser informado");
        } else if(marcaRepository.existsByNome(dto.nome()) == true){
            throw new ValidationException("nome", "O nome já existe");
        }

        Marca marca = new Marca();

        marca.setNome(dto.nome());

        marcaRepository.persist(marca);

        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    @Transactional
    public MarcaResponseDTO update(Long id, MarcaDTO dto) {
        if(dto.nome().isBlank() || dto.nome().isEmpty()){
            throw new ValidationException("nome", "O nome deve ser informado");
        } else if(marcaRepository.existsByNome(dto.nome()) == true){
            throw new ValidationException("nome", "O nome já existe");
        } else if(marcaRepository.findById(id) == null){
            throw new NotFoundException("Cadastro não encontrado");
        }

        Marca marca = marcaRepository.findById(id);

        marca.setNome(dto.nome());

        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Verifique se a marca está sendo referenciada por outra classe
        if (marcaRepository.isBeingReferencedByAnotherEntity(id)) {
            // Lança uma exceção caso a marca esteja sendo referenciada
            throw new ValidationException("delete", "A marca não pode ser excluída porque está sendo referenciada por outra entidade.");
        }
    
        // Tente deletar a marca pelo ID
        if (!marcaRepository.deleteById(id)) {
            // Lança exceção caso o ID não seja encontrado
            throw new NotFoundException("Id não encontrado");
        }
    }
    
    


    @Override
    public MarcaResponseDTO getByNome(String nome) {
        if(marcaRepository.findByNome(nome) == null){
            throw new NotFoundException("Marca não encontrada");
        }
        return MarcaResponseDTO.valueOf(marcaRepository.findByNome(nome));
    }

    @Override
    public MarcaResponseDTO getById(Long id) {
        if(marcaRepository.findById(id) == null){
            throw new NotFoundException("Marca não encontrada");
        }
        return MarcaResponseDTO.valueOf(marcaRepository.findById(id));
    }
    
}
