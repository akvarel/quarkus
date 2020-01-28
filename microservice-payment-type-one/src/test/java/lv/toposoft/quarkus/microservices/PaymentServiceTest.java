package lv.toposoft.quarkus.microservices;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PaymentServiceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/type1")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}