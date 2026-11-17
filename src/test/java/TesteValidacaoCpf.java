import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TesteValidacaoCpf {

    String url = "http://localhost:8080/exercicios";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void exercicio06_TesteValidarCpf() {

        String url = "/validarCpf";
        String msgResponseBody = "CPF Valido";
        String cpf = "00407939237";

        given()
                .spec(requestSpecification)
                .queryParam("cpf", cpf)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(equalTo(msgResponseBody));
    }
}
