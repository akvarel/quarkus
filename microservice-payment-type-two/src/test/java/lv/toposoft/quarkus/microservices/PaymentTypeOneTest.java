package lv.toposoft.quarkus.microservices;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PaymentTypeOneTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/type2")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}