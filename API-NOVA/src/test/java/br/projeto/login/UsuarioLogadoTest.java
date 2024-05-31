package br.projeto.login;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import br.projeto.petshop.dto.UsuarioDTO;
import br.projeto.petshop.dto.UsuarioResponseDTO;
import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.repository.UsuarioRepository;
import br.projeto.petshop.service.HashService;
import br.projeto.petshop.service.JwtService;
import br.projeto.petshop.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class UsuarioLogadoTest {

    @Inject
    UsuarioService usuarioService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Test
    public void testGetUsuario() {
        // Inserindo o novo usuario

        UsuarioDTO dto = new UsuarioDTO("Fulano", "de tal", "77733399984", "fulaninho_br", "fulaninho@gmail.com",
                hashService.getHashSenha("12345"), Perfil.valueOf(1), null);
        usuarioService.insert(dto);

        String token = jwtService.generateJwt(usuarioService.findByEmail("fulaninho@gmail.com"));

        given().header("Authorization", "Bearer " + token).when().get("/usuarioLogado").then().statusCode(200);
    }

}
