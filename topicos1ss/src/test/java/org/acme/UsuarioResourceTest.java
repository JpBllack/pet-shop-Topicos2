package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class UsuarioResourceTest {

    private String token;

    @BeforeEach
    public void setAuth() {
        // Substitua pelas credenciais de um usuário admin válido
        AuthUsuarioDTO auth = new AuthUsuarioDTO("joaojoao", "123");

        Response response = given()
                .contentType("application/json")
                .body(auth)
                .when().post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.header("Authorization");
    }

    @Test
    public void getAllTeste() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/usuario")
                .then()
                .statusCode(200)
                .body(is(not(empty())));
    }

    @Test
    public void getIdTeste() {
        String testeId = "testestestesteste";
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/usuario/" + testeId)
                .then()
                .statusCode(200)
                .body("id", is(testeId));
    }

    @Test
    public void updateNomeTest() {
        String testeId = "testestestesteste";
        UsuarioUpdateNomeDTO nomeDTO = new UsuarioUpdateNomeDTO("Nome Atualizado");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(nomeDTO)
                .when().patch("/usuario/update/nome/" + testeId)
                .then()
                .statusCode(200);
    }
    @Test
    public void updateLoginTest() {
        String testeId = "testestestesteste";
        UsuarioUpdateLoginDTO loginDTO = new UsuarioUpdateLoginDTO("LoginAtualizado");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(loginDTO)
                .when().patch("/usuario/update/login/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    public void updateSenhaTest() {
        String testeId = "testestestesteste";
        UsuarioUpdateSenhaDTO senhaDTO = new UsuarioUpdateSenhaDTO("112233");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(senhaDTO)
                .when().patch("/usuario/update/senha/" + testeId)
                .then()
                .statusCode(200);
    }
    @Test
    public void promoverAdminTest() {
        String testeId = "testestestesteste2";

        given()
                .header("Authorization", "Bearer " + token)
                .when().patch("/usuario/promoveradmin/" + testeId)
                .then()
                .statusCode(200);
    }


    @Test
    public void insertTeste() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("12345678901", "Novo Usuario", "novo@usuario.com", "novousuario", "senha123");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(usuarioDTO)
                .when().post("/usuario")
                .then()
                .statusCode(200)
                .body("cpf", is("12345678901"),
                        "nome", is("Novo Usuario"));
    }

    @Test
    public void getCpfTest() {
        String testeCpf = "45678901234"; // Substitua pelo CPF de um usuário válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/usuario/cpf/" + testeCpf)
                .then()
                .statusCode(200);
    }


    @Test
    public void deleteTeste() {
        String testeId = "testestestesteste";
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/usuario/delete/" + testeId)
                .then()
                .statusCode(200);
    }

    // Adicione testes para outros métodos conforme necessário
}
