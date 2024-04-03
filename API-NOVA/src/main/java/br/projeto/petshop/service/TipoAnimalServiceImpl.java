package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.TipoAnimalDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoAnimalServiceImpl implements TipoAnimalService{

    @Override
    public List<TipoAnimalDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public TipoAnimalDTO insert(TipoAnimalDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public TipoAnimalDTO update(TipoAnimalDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public TipoAnimalDTO findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public TipoAnimalDTO findByNome(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }
    
}
