package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.CategoriaDTO;
import org.acme.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {

    List<CategoriaResponseDTO> getAll();

    CategoriaResponseDTO getId(long id);

    List<CategoriaResponseDTO> getNome(String nome);

    Response insert(CategoriaDTO categoriaDTO);

    Response delete(long id);
}
