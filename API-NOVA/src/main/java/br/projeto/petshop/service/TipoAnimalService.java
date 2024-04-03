package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.TipoAnimalDTO;

public interface TipoAnimalService {

    public List<TipoAnimalDTO> getAll();

    public TipoAnimalDTO insert(TipoAnimalDTO dto);

    public TipoAnimalDTO update(TipoAnimalDTO dto);

    public void delete(Long id);

    public TipoAnimalDTO findById(Long id);

    public TipoAnimalDTO findByNome(String nome);
}
