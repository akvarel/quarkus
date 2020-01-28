package lv.toposoft.quarkus.microservices.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/*
Client should be able to create payment of one of 3 types - TYPE1, TYPE2, TYPE3. Fields 'amount' (positive decimal), 'currency' (EUR or USD), 'debtor_iban' and 'creditor_iban' (texts) are mandatory for all types.
Additional type-specific requirements:
TYPE1 is only applicable for EUR payments, has additional field 'details' (text) which is mandatory;
TYPE2 is only applicable for USD payments, has additional field ‘details’ (text) which is optional.
TYPE3 is applicable for payments in both EUR and USD currency, has additional field for creditor bank BIC code (text) which is mandatory.
 */
@Schema(description="The payment entry")
public class Payment {
    @NotNull
    public String payment_type;
    @NotNull
    @Positive
    public BigDecimal amount;
    @NotNull
    public String currency;
    @NotNull
    public String debtor_iban;
    @NotNull
    public String creditor_iban;
    public String creditor_bic;
    public String details;

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
            '}';
    }
}
