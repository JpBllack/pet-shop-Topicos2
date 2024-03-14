package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.VendaPagamentoDTO;
import org.acme.dto.VendaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendaResourceTest {

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
    public void getAllTest() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/venda")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void getIdTest() {
        long vendaId = 1L;
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/venda/" + vendaId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void insertPagamentoTest() {
        VendaPagamentoDTO vendaPagamentoDTO = new VendaPagamentoDTO(4L, 4L);
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(vendaPagamentoDTO)
                .when().patch("/venda/pagamento")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void insertVendaTest() {
        VendaDTO vendaDTO = new VendaDTO(1L, 1L);
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(vendaDTO)
                .when().post("/venda")
                .then()
                .statusCode(200);
    }
}
