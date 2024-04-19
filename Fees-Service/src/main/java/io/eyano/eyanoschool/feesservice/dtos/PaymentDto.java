package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.eyano.eyanoschool.feesservice.entitiesExt.Currency;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import io.eyano.eyanoschool.feesservice.entitiesExt.Pupil;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
/**
 * PaymentDto class is used to represent the payment data transfer object
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 */
@Data @NoArgsConstructor @ToString
public class PaymentDto  {
    private Long id;
    @NotNull (message = "The field is required")
    private Double amount;
    @NotNull (message = "The field is required")
    private LocalDate date;

    private Pupil pupil;
    @NotNull (message = "The field is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idPupil;

    private String nameFirstnameUser;
    @NotNull (message = "The field is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idUser;

    private PaymentSystem paymentSystem;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull (message = "The field is required")
    private Long idPaymentSystem;

    private Currency currency;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull (message = "The field is required")
    private Long idCurrency;
    @NotNull (message = "The field is required")
    private FeesDto fees;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean remove;

}
