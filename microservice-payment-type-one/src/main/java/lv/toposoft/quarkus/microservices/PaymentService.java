package lv.toposoft.quarkus.microservices;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;

@ApplicationScoped
public class PaymentService {
    // tag::adocKafkaEmitter[]
    @Inject
    @Channel("payment-channel")
    Emitter<Payment> emitter;
    // end::adocKafkaEmitter[]

    @ConfigProperty(name = "payment.cancel.fee", defaultValue = "0.05")
    public BigDecimal cancelFee;

    public Payment persistPayment(Payment payment){
        payment.created = Instant.now();
        payment.status = "new";
        payment.persist();
        emitter.send(payment);
        return payment;
    }
}
