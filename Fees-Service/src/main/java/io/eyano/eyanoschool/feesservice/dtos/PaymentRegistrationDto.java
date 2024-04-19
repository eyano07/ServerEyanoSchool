package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
/**
 * PaymentRegistrationDto class is used to represent the payment registration data transfer object
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 */
@Data @NoArgsConstructor @ToString
public class PaymentRegistrationDto  {
    private Long id;
    @NotNull (message = "The field is required")
    private LocalDate date;
    @NotNull (message = "The field is required")
    private Double amount;

    private PaymentSystem paymentSystem;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull (message = "The field is required")
    private Long idPaymentSystem;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull (message = "The field is required")
    private Long idUser;
    private String nameFirstnameUser;

    private String nameFirstnameCandidate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull (message = "The field is required")
    private Long idCandidate;
    @NotNull (message = "The field is required")
    private FeesDto fees;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean remove;
}
