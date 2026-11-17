package pet_store;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExemploPetStore {

    String url = "https://petstore.swagger.io/v2";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void adicionarNovoUsuario() {

        String bodyUser = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"QA Teste\",\n" +
                "    \"firstName\": \"QA\",\n" +
                "    \"lastName\": \"Teste\",\n" +
                "    \"email\": \"qateste@teste.com\",\n" +
                "    \"password\": \"123teste\",\n" +
                "    \"phone\": \"619999999999\",\n" +
                "    \"userStatus\": 1\n" +
                "  }\n" +
                "]";

        String path = "/user/createWithList";

        given()
                .spec(requestSpecification)
                .log().all()
                .header("Content-Type", "application/json")
                .body(bodyUser)
                .when()
                .post(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
