package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.EstadoDTO;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EstadoResourceTest {

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
                .when().get("/estado")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void getIdTeste() {
        Long testeId = 4L; // Substitua pelo ID de um estado válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/estado/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void insertTest() {
        EstadoDTO estadoDTO = new EstadoDTO("São Paulo", "SP");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(estadoDTO)
                .when().post("/estado")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void updateTest() {
        Long testeId = 1L; // Substitua pelo ID de um estado válido
        EstadoDTO estadoDTO = new EstadoDTO("Rio de Janeiro", "RJ");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(estadoDTO)
                .pathParam("id", testeId)
                .when().patch("/estado/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void deleteTest() {
        Long testeId = 4L; // Substitua pelo ID de um estado válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/estado/" + testeId)
                .then()
                .statusCode(200);
    }
}
