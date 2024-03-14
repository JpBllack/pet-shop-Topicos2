package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.model.Usuario;
import org.acme.service.AuthService;
import org.acme.service.HashService;
import org.acme.service.TokenJwtService;
import org.acme.service.UsuarioService;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;


    @Inject
    TokenJwtService tokenService;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    AuthService service;

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthUsuarioDTO authDTO) {
        return service.login(authDTO);

    }
}
