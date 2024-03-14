package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.EnderecoDTO;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnderecoResourceTest {

    private String token;

    @BeforeEach
    public void setAuth() {
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
    @Order(1)
    public void getAllTeste() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/endereco")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void getIdTeste() {
        Long testeId = 5L; // Substitua pelo ID de um endereço válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/endereco/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void insertTest() {
        String usuarioId = "213sdfdsas-aasd23234"; // Substitua pelo ID do usuário
        EnderecoDTO enderecoDTO = new EnderecoDTO("Rua Exemplo", "123", "Apt 456", "Bairro Exemplo", 1L, "12345-678");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(enderecoDTO)
                .pathParam("id", usuarioId)
                .when().post("/endereco/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void deleteTest() {
        long testeId = 5L; // Substitua pelo ID de um endereço válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/endereco/delete/" + testeId)
                .then()
                .statusCode(200);
    }
}
