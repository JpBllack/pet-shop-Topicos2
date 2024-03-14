package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.BoletoBancarioDTO;
import org.acme.dto.BoletoBancarioResponseDTO;

import java.util.List;

public interface BoletoBancarioService {

    List<BoletoBancarioResponseDTO> getAll();

    BoletoBancarioResponseDTO getId(long id);

    Response insert(BoletoBancarioDTO boletoBancarioDTO);

    Response delete(long id);
}
