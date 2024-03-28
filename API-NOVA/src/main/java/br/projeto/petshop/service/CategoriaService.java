package br.projeto.petshop.service;

import jakarta.ws.rs.core.Response;
import br.projeto.petshop.dto.CategoriaDTO;
import br.projeto.petshop.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {

    List<CategoriaResponseDTO> getAll();

    CategoriaResponseDTO getId(long id);

    List<CategoriaResponseDTO> getNome(String nome);

    Response insert(CategoriaDTO categoriaDTO);

    Response delete(long id);
}
