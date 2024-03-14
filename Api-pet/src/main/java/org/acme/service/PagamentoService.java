package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.PagamentoDTO;
import org.acme.dto.PagamentoResponseDTO;

import java.util.List;

public interface PagamentoService {

    List<PagamentoResponseDTO> getAll();

    PagamentoResponseDTO getId(long id);

    List<PagamentoResponseDTO> getByTipo(String tipo);

    Response insert(PagamentoDTO pagamentoDTO);

    Response delete(long id);
}
