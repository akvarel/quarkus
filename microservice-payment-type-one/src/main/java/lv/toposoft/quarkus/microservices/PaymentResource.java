package lv.toposoft.quarkus.microservices;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
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
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Payment.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No payments")
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
    public Response createPayment(@Valid Payment payment){
        payment = paymentService.persistPayment(payment);
        return Response.ok(payment).build();
    }

    @Operation(summary = "Returns a payment for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Payment.class)))
    @APIResponse(responseCode = "204", description = "The payment is not found for a given identifier")
    // tag::adocMetrics[]
    @Counted(name = "countGetPayment", description = "Counts how many times the getPayment method has been invoked")
    @Timed(name = "timeGetPayment", description = "Times how long it takes to invoke the getPayment method", unit = MetricUnits.MILLISECONDS)
    // end::adocMetrics[]
    @GET
    @Path("/{id}")
    public Response getPayment(@Parameter(description = "Payment identifier", required = true) @PathParam("id") String id){
        Payment payment = paymentService.findPaymentById(id);
        if (payment != null) {
            LOGGER.debug("Found payment " + payment);
            return Response.ok(payment).build();
        } else {
            LOGGER.debug("No payment found with id " + id);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Cancel a payment for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Payment.class)))
    @APIResponse(responseCode = "204", description = "The payment is not found for a given identifier or too late to cancel")
    // tag::adocMetrics[]
    @Counted(name = "countCancelPayment", description = "Counts how many times the cancelPayment method has been invoked")
    @Timed(name = "timeCancelPayment", description = "Times how long it takes to invoke the cancelPayment method", unit = MetricUnits.MILLISECONDS)
    // end::adocMetrics[]
    @DELETE
    @Path("/{id}")
    public Response cancelPayment(@Parameter(description = "Payment identifier", required = true) @PathParam("id") String id){
        Payment payment = paymentService.cancelPayment(id);
        if (payment != null) {
            LOGGER.debug("Found payment " + payment);
            return Response.ok(payment).build();
        } else {
            LOGGER.debug("No payment found with id " + id);
            return Response.noContent().build();
        }
    }

}
