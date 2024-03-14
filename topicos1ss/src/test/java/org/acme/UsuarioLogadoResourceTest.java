package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioLogadoResourceTest {

    private String token;
    private String token2;

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
    public void getPerfilUsuarioLogadoTeste() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/usuariologado")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void getVendasTeste() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/usuariologado/venda")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void updateSenhaTeste() {
        UsuarioLogadoMudarSenhaDTO senhaDTO = new UsuarioLogadoMudarSenhaDTO( "123", "1234");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(senhaDTO)
                .when().patch("/usuariologado/update/senha")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void updateLoginTeste() {
        UsuarioUpdateLoginDTO loginDTO = new UsuarioUpdateLoginDTO("novoLogin");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(loginDTO)
                .when().patch("/usuariologado/update/login")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void updateNomeTeste() {
        UsuarioUpdateNomeDTO nomeDTO = new UsuarioUpdateNomeDTO("Novo Nome");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(nomeDTO)
                .when().patch("/usuariologado/update/nome")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(6)
    public void updateEmailTeste() {
        UsuarioUpdateEmailDTO emailDTO = new UsuarioUpdateEmailDTO("novoemail@gmail.com");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(emailDTO)
                .when().patch("/usuariologado/update/email")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(7)
    public void insertTelefoneTeste() {

        TelefoneDTO telefoneDTO = new TelefoneDTO("11", "123456789");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(telefoneDTO)
                .when().patch("/usuariologado/insert/telefone")
                .then()
                .statusCode(204);
                //Ele ja tem um telefone salvo
    }

    @Test
    @Order(8)
    public void updateTelefoneTeste() {
        TelefoneDTO dto = new TelefoneDTO("63", "984555244");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().patch("/usuariologado/update/telefone")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(9)
    public void insertEnderecoTeste() {
        EnderecoDTO enderecoDTO = new EnderecoDTO("Rua Nova", "123", "Apto 01", "Bairro", 1L, "12345-678");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(enderecoDTO)
                .when().patch("/usuariologado/insert/endereco")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(10)
    public void updateEnderecoTeste() {
        EnderecoDTO enderecoDTO = new EnderecoDTO("Rua Nova", "123", "Apto 01", "Bairro", 1L, "12345-678");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(enderecoDTO)
                .when().patch("/usuariologado/update/endereco/1")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(11)
    public void deleteOnTeste() {
        AuthUsuarioDTO auth2 = new AuthUsuarioDTO("testeste2", "123");

        Response response = given()
                .contentType("application/json")
                .body(auth2)
                .when().post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        token2 = response.header("Authorization");

        given()
                .header("Authorization", "Bearer " + token2)
                .when().delete("/usuariologado/deleteon")
                .then()
                .statusCode(200);
    }
}
