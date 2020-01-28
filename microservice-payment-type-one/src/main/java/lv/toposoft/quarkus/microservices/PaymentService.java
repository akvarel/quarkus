package lv.toposoft.quarkus.microservices;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class PaymentService {
    // tag::adocKafkaEmitter[]
    @Inject
    @Channel("payment-channel")
    Emitter<Payment> emitter;
    // end::adocKafkaEmitter[]

    @ConfigProperty(name = "payment.cancel.fee", defaultValue = "0.05")
    BigDecimal cancelFee;

    public Payment cancelPayment(String id){
        Payment payment = Payment.findById(id);
        Instant now = Instant.now();
        int nowDay = now.atOffset(ZoneOffset.UTC).toLocalDate().getDayOfYear();
        if(payment != null && nowDay == payment.created.atOffset(ZoneOffset.UTC).toLocalDate().getDayOfYear()) {
            long diffAsHours = ChronoUnit.HOURS.between(payment.created, now);
            BigDecimal h = BigDecimal.valueOf(diffAsHours);
            payment.canceled = now;
            payment.cancellation_fee = cancelFee.multiply(h);
            payment.status = "cancel";
            payment.persistOrUpdate();
            emitter.send(payment);
            return payment;
        }
        return null;
    }

    public Payment persistPayment(Payment payment){
        payment.created = Instant.now();
        payment.status = "new";
        payment.persist();
        emitter.send(payment);
        return payment;
    }

    public Payment findPaymentById(String id) {
        return Payment.findById(id);
    }
}
