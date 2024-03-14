package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.CidadeDTO;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CidadeResourceTest {

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
                .when().get("/cidade")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void getIdTeste() {
        Long testeId = 4L; // Substitua pelo ID de uma cidade válida
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/cidade/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void insertTest() {
        CidadeDTO cidadeDTO = new CidadeDTO("NovaCidade", 1L); // Substitua 1L pelo ID de um estado válido

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(cidadeDTO)
                .when().post("/cidade")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void updateTest() {
        Long testeId = 4L; // Substitua pelo ID de uma cidade válida
        CidadeDTO cidadeDTO = new CidadeDTO("CidadeAtualizada", 1L); // Substitua 1L pelo ID de um estado válido

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(cidadeDTO)
                .pathParam("id", testeId)
                .when().put("/cidade/update/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void deleteTest() {
        long testeId = 4L; // Substitua pelo ID de uma cidade válida
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/cidade/delete/" + testeId)
                .then()
                .statusCode(200);
    }
}
