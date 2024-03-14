package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.CategoriaDTO;
import org.acme.dto.TelefoneDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TelefoneResourceTest {

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
    @Order(2)
    public void getAllTeste() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/telefone")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void getIdTeste() {
        Long testeId = 1L; // Substitua pelo ID de um telefone válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/telefone/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void getCodigoAreaTeste() {
        String codigoArea = "63"; // Substitua pelo código de área válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/telefone/codigo/" + codigoArea)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void insertTest() {
        TelefoneDTO dto = new TelefoneDTO("011", "12345678"); // Substitua com dados válidos

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().post("/telefone/testestestesteste")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(7)
    public void deleteTest() {
        Long testeId = 1L; // Substitua pelo ID de um telefone válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/telefone/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(6)
    public void updateTest() {
        Long testeId = 1L; // Substitua pelo ID de um telefone válido
        TelefoneDTO dto = new TelefoneDTO("021", "87654321"); // Substitua com dados válidos

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().put("/telefone/" + testeId)
                .then()
                .statusCode(200);
    }

    // Testes adicionais para outros endpoints podem ser adicionados aqui
}
