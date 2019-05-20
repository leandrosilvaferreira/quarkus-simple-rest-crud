package org.acme.quarkus.sample;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PessoasResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/pessoa")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}