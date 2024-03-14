package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.PixPagamentoDTO;
import org.acme.dto.PixPagamentoResponseDTO;
import java.util.List;

public interface PixPagamentoService {

    List<PixPagamentoResponseDTO> getAll();

    PixPagamentoResponseDTO getId(long id);

    Response insert(PixPagamentoDTO pixPagamentoDTO);

    Response update(Long id, PixPagamentoDTO pixPagamentoDTO);

    Response delete(Long id);
}
