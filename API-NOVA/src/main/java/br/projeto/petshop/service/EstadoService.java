package br.projeto.petshop.service;

import br.projeto.petshop.dto.EstadoDTO;

import java.util.List;

public interface EstadoService {

    List<EstadoDTO> getAll();

    EstadoDTO getById(long id);

    void insert(EstadoDTO estadoDTO);

    void update(long id, EstadoDTO estadoDTO);

    void delete(long id);
}
