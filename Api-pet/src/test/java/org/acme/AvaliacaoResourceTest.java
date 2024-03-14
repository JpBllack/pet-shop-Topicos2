package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AvaliacaoDTO;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

import org.acme.dto.AuthUsuarioDTO;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AvaliacaoResourceTest {

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
                .when().get("/avaliacao")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void getIdTeste() {
        Long testeId = 1L; // Substitua pelo ID de uma avaliação válida
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/avaliacao/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void insertTest() {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(5, "Muito bom");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(avaliacaoDTO)
                .when().post("/avaliacao")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void deleteTest() {
        long testeId = 1L; // Substitua pelo ID de uma avaliação válida
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/avaliacao/delete/" + testeId)
                .then()
                .statusCode(200);
    }
}