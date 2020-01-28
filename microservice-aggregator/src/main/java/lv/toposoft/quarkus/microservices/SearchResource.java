package lv.toposoft.quarkus.microservices;

import lv.toposoft.quarkus.microservices.client.PaymentTypeOneService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/payments")
@RegisterRestClient
public class SearchResource {
    @Inject
    PaymentTypeOneService paymentTypeOneService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPayments() {
        return "hello";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getPaymentById(@PathParam("id") String id) {
        return "id";
    }

}