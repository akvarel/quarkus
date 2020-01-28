package lv.toposoft.quarkus.microservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/type1")
public class PaymentService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String payment() {
        return "hello";
    }
}