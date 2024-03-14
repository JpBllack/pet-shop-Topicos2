package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.ProdutoDTO;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutoResourceTest {

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
                .when().get("/produto")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void getIdTeste() {
        Long testeId = 4L; // Substitua pelo ID de um produto válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/produto/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void getNomeTeste() {
        String nome = "Iphone"; // Substitua pelo nome de um produto válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/produto/nome/" + nome)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void insertTest() {
        ProdutoDTO dto = new ProdutoDTO("Nome do Produto", "Descrição do Produto", 100.0, 10, 1L); // Substitua com dados válidos

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().post("/produto")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(6)
    public void retiraEstoqueTeste() {
        Long testeId = 4L; // Substitua pelo ID de um produto válido
        int quantidade = 5; // Quantidade a ser retirada

        given()
                .header("Authorization", "Bearer " + token)
                .when().patch("/produto/retiraestoque/" + testeId + "/" + quantidade)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(7)
    public void adicionaEstoqueTeste() {
        Long testeId = 4L; // Substitua pelo ID de um produto válido
        int quantidade = 10; // Quantidade a ser adicionada

        given()
                .header("Authorization", "Bearer " + token)
                .when().patch("/produto/adicionaestoque/" + testeId + "/" + quantidade)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(8)
    public void updateTest() {
        Long testeId = 4L; // Substitua pelo ID de um produto válido
        ProdutoDTO dto = new ProdutoDTO("Nome Atualizado", "Descrição Atualizada", 150.0, 5, 1L); // Substitua com dados válidos

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().patch("/produto/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(9)
    public void deleteTest() {
        Long testeId = 4L; // Substitua pelo ID de um produto válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/produto/" + testeId)
                .then()
                .statusCode(200);
    }

    // Testes adicionais para os outros endpoints podem ser adicionados aqui
}
