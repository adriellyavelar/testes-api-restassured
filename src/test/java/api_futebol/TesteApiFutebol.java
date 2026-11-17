package api_futebol;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TesteApiFutebol {

    String url = "https://api.api-futebol.com.br/v1";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void exercicio01_ApiFutebol() {

        String path = "/campeonatos";

        given()
                .spec(requestSpecification)
                .log().all()
                .header("Authorization", "Bearer test_4f24b86ec6fcd4f639fcc32aa77b56")
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString("Campeonato Brasileiro 2025"));
    }
}
