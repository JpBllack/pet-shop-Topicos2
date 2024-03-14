package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.dto.AuthUsuarioDTO;

public interface AuthService {
    public Response login(AuthUsuarioDTO authDTO);
}
