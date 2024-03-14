package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.CategoriaDTO;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaResourceTest {

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
                .when().get("/categoria")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void getIdTeste() {
        Long testeId = 1L; // Substitua pelo ID de uma categoria válida
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/categoria/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void getNomeTeste() {
        String nomeTeste = "Smartphone"; // Substitua pelo nome de uma categoria válida
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/categoria/nome/" + nomeTeste)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void insertTest() {
        CategoriaDTO categoriaDTO = new CategoriaDTO("NovaCategoria");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(categoriaDTO)
                .when().post("/categoria")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void deleteTest() {
        long testeId = 5L; // Substitua pelo ID de uma categoria válida
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/categoria/delete/" + testeId)
                .then()
                .statusCode(200);
    }
}
