package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.ItemVendaDTO;
import org.acme.dto.ItemVendaResponseDTO;

import java.util.List;

public interface ItemVendaService {

    List<ItemVendaResponseDTO> getAll();

    ItemVendaResponseDTO getId(long id);

    Response insert(ItemVendaDTO itemVendaDTO);

    Response delete(long id);
}
