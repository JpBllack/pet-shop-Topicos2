package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.EnderecoDTO;
import org.acme.dto.EnderecoResponseDTO;

import java.util.List;

public interface EnderecoService {

    List<EnderecoResponseDTO> getAll();

    EnderecoResponseDTO getId(Long id);

    Response insert(String idUsuario, EnderecoDTO enderecoDTO);

    Response delete(long id);
}
