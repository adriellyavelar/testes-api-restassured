package api_futebol;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.in;

public class TesteApiFutebol {

    String url = "https://api.api-futebol.com.br/v1";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void exercicio_ApiFutebol() {

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

    @Test
    public void exemploExtracaoDeInformacoesJsonPath() {

        String path = "/campeonatos/10/tabela";

        String primeiroColocado = given()
                .spec(requestSpecification)
                .log().all()
                .header("Authorization", "Bearer test_4f24b86ec6fcd4f639fcc32aa77b56")
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("[0] .time.nome_popular");

        System.out.println("Primeiro colocado é: "+primeiroColocado);
    }

    @Test
    public void exemploExtracaoDeInformacoesUtilizandoResponseJsonPath() {

        Response response;

        String path = "/campeonatos/10/tabela";

        response = given()
                .spec(requestSpecification)
                .log().all()
                .header("Authorization", "Bearer test_4f24b86ec6fcd4f639fcc32aa77b56")
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        String primeiroColocado, segundoColocado, terceiroColocado, quartoColocado;

        primeiroColocado = response.path("[0] .time.nome_popular");
        segundoColocado = response.path("[1] .time.nome_popular");
        terceiroColocado = response.path("[2] .time.nome_popular");
        quartoColocado = response.path("[3] .time.nome_popular");

        System.out.println("Primeiro colocado é: "+primeiroColocado);
        System.out.println("Segundo colocado é: "+segundoColocado);
        System.out.println("Terceiro colocado é: "+terceiroColocado);
        System.out.println("Quarto colocado é: "+quartoColocado);
    }

    @Test
    public void exercicioTabelaCampeonato01E02() {

        Response response;

        String path = "/campeonatos/10/tabela";

        response = given()
                .spec(requestSpecification)
                .log().all()
                .header("Authorization", "Bearer test_4f24b86ec6fcd4f639fcc32aa77b56")
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        for (int i = 0; i < 20; i++) {
            System.out.println((i+1) +"º - "+ response.path("["+i+"] .time.nome_popular"));
        }

        int primeiroColocado = response.path("[0] .pontos");
        int ultimoColocado = response.path("[19] .pontos");

        Assert.assertTrue("Primeiro colocado tem mais pontos que o último", primeiroColocado > ultimoColocado);

    }
}
