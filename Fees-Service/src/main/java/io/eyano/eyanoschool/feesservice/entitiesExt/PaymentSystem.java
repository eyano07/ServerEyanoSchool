package io.eyano.eyanoschool.feesservice.entitiesExt;

import io.eyano.eyanoschool.feesservice.enums.PaymentMethode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
/**
 * This is a class which represents an external entity coming from the Financial-Service module
 * @author : Pascal
 * @since : 02/02/2021
 * @version : 1.0
 */
@Data @Builder @ToString
public class PaymentSystem {
    private Long id;
    private PaymentMethode paymentMethod;
    private String institutionName;
    private String designation;
    private String accountNumberOrPhoneNumber;
    private String transactionNumber;
    private String clientName;
    private String clientPhoneNumber;
}
