package lv.toposoft.quarkus.microservices;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.Decimal128;

/*
Client should be able to create payment of one of 3 types - TYPE1, TYPE2, TYPE3. Fields 'amount' (positive decimal), 'currency' (EUR or USD), 'debtor_iban' and 'creditor_iban' (texts) are mandatory for all types.
Additional type-specific requirements:
TYPE1 is only applicable for EUR payments, has additional field 'details' (text) which is mandatory;
TYPE2 is only applicable for USD payments, has additional field ‘details’ (text) which is optional.
TYPE3 is applicable for payments in both EUR and USD currency, has additional field for creditor bank BIC code (text) which is mandatory.
 */
@MongoEntity
public class Payment extends PanacheMongoEntity {
    public PaymentType payment_type;
    public Decimal128 amount;
    public String currency;
    public String debtor_iban;
    public String creditor_iban;
    public String creditor_bic;
    public String details;
}
