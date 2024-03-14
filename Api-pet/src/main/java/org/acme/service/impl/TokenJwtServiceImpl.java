package org.acme.service.impl;

import io.quarkus.logging.Log;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Usuario;
import org.acme.service.TokenJwtService;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class TokenJwtServiceImpl implements TokenJwtService {

    public static final Logger LOG = Logger.getLogger(TokenJwtServiceImpl.class);

    private static final Duration EXPIRATION_TIME = Duration.ofDays(200);

    @Override
    public String generateJwt(Usuario usuario) {

        try {
            Instant now = Instant.now();

            Instant expiryDate = now.plus(EXPIRATION_TIME);

            Set<String> roles = usuario.getPerfis()
                    .stream().map(p -> p.getLabel())
                    .collect(Collectors.toSet());

            Log.info("Requisição TokenJwt.generateJwt()");

            return Jwt.issuer("unitins-jwt")
                    .subject(usuario.getId())
                    .groups(roles)
                    .expiresAt(expiryDate)
                    .sign();

        } catch (Exception e) {
            Log.error("Erro ao rodar Requisição TokenJwt.generateJwt()");
            return null;
        }

    }

}
