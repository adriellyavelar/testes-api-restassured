import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TesteExercicios {

    String url = "http://localhost:8080/exercicios";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void exercicio04_TesteParOuImpar() {

        String path = "/parOuImpar";
        String msgResponseBody = "O numero 2 é par";

        given()
                .spec(requestSpecification)
                .queryParam("numero", 2)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(equalTo(msgResponseBody));
    }

    @Test
    public void exercicio05_TesteCalculaSalario() {

        String pathParamSalario = "3000";
        String path = "/calculaSalario/"+pathParamSalario;

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString("Salario liquido = 2050.6516"));
    }
}
