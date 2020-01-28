package lv.toposoft.quarkus.microservices;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/payments")
@Produces(APPLICATION_JSON)
public class PaymentResource {

    private static final Logger LOGGER = Logger.getLogger(PaymentResource.class);

    @Inject
    PaymentService paymentService;

    // tag::adocMetricsMethods[]
    // tag::adocOpenAPI[]
    @Operation(summary = "Returns list of payments")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Payment.class, required = true)))
    // end::adocOpenAPI[]
    // tag::adocMetrics[]
    @Counted(name = "countGetPayments", description = "Counts how many times the getPayments method has been invoked")
    @Timed(name = "timeGetPayments", description = "Times how long it takes to invoke the getPayments method", unit = MetricUnits.MILLISECONDS)
    // end::adocMetrics[]
    @GET
    @Produces(APPLICATION_JSON)
    public Response getPayments() {
        List<Payment> paymentList = Payment.findAll().list();
        return Response.ok(paymentList).build();
    }
    // end::adocMetricsMethods[]

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Payment createPayment(@Valid Payment payment){
        payment.persist();
        return payment;
    }

    @GET
    @Path("/{id}")
    public Response getPayment(@Parameter(description = "Payment identifier", required = true) @PathParam("id") String id){
        return Response.ok().build();
    }
}
