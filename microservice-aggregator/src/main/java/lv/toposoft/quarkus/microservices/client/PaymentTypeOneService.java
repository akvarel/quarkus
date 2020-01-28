package lv.toposoft.quarkus.microservices.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface PaymentTypeOneService {

    @GET
    Payment findAllPayments();

    @GET
    @Path("/{id}")
    Payment findPaymentById(@PathParam("id") String id);

}
