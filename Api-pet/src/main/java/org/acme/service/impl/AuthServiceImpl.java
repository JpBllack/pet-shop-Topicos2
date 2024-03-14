package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.model.Usuario;
import org.acme.service.AuthService;
import org.acme.service.HashService;
import org.acme.service.TokenJwtService;
import org.acme.service.UsuarioService;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    public static final Logger LOG = Logger.getLogger(AuthServiceImpl.class);

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    TokenJwtService tokenService;


    @Override
    public Response login(AuthUsuarioDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario usuario = usuarioService.findByLoginAndSenha(authDTO.login(), hash);

        if (usuario == null) {
            LOG.error("erro ao rodar requisição login()");
            return Response.status(Status.NO_CONTENT)
                    .entity("Usuario não encontrado").build();
        }

        LOG.info("requisição Auth.login()");
        return Response.ok()
                .header("Authorization", tokenService.generateJwt(usuario))
                .build();

    }
}
