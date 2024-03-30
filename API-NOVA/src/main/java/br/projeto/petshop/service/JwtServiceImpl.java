package br.projeto.petshop.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.projeto.petshop.dto.UsuarioResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioResponseDTO dto) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        // exemplo para teste
        Set<String> roles = new HashSet<String>();
        
        roles.add(dto.perfil().getLabel());

        return Jwt.issuer("unitins-jwt")
            .subject(dto.email())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();
    }
    
}
