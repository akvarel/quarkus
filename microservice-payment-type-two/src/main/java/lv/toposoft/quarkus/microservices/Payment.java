package lv.toposoft.quarkus.microservices;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.Decimal128;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;

/*
Client should be able to create payment of one of 3 types - TYPE1, TYPE2, TYPE3. Fields 'amount' (positive decimal), 'currency' (EUR or USD), 'debtor_iban' and 'creditor_iban' (texts) are mandatory for all types.
Additional type-specific requirements:
TYPE1 is only applicable for EUR payments, has additional field 'details' (text) which is mandatory;
TYPE2 is only applicable for USD payments, has additional field ‘details’ (text) which is optional.
TYPE3 is applicable for payments in both EUR and USD currency, has additional field for creditor bank BIC code (text) which is mandatory.
 */
@MongoEntity(collection = "typeTwo")
public class Payment extends PanacheMongoEntity {
    @NotNull
    public String payment_type;
    @NotNull
    @Positive
    public BigDecimal amount;
    @NotNull
    @Pattern(message = "Invalid currency: ${validatedValue}", regexp = "USD")
    public String currency;
    @NotNull
    public String debtor_iban;
    @NotNull
    public String creditor_iban;
    public String creditor_bic;
    public String details;

    public String status;
    public Instant created;
    public Instant canceled;
    public BigDecimal cancellation_fee;

    @Override
    public String toString() {
        return "Payment{" +
            "payment_type='" + payment_type + '\'' +
            ", amount=" + amount +
            ", currency='" + currency + '\'' +
            ", debtor_iban='" + debtor_iban + '\'' +
            ", creditor_iban='" + creditor_iban + '\'' +
            ", creditor_bic='" + creditor_bic + '\'' +
            ", details='" + details + '\'' +
            ", status='" + status + '\'' +
            ", created=" + created +
            ", canceled=" + canceled +
            ", cancellation_fee=" + cancellation_fee +
            ", id=" + id +
            '}';
    }

}
