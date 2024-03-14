package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.CartaoCreditoDTO;
import org.acme.dto.CartaoCreditoResponseDTO;

import java.util.List;

public interface CartaoCreditoService {

    List<CartaoCreditoResponseDTO> getAll();

    CartaoCreditoResponseDTO getId(long id);

    Response insert(CartaoCreditoDTO cartaoCreditoDTO);

    Response delete(long id);
}
