package lv.toposoft.quarkus.microservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/payments")
public class PaymentService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String payment() {
        return "hello";
    }
}
