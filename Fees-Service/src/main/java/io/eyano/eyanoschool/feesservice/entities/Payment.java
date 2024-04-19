package io.eyano.eyanoschool.feesservice.entities;

import io.eyano.eyanoschool.feesservice.entitiesExt.Currency;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import io.eyano.eyanoschool.feesservice.entitiesExt.Pupil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
/**
 * This class represents the Payment table in the database
 * @author : Pascal Tshingila
 * @since : 02/02/2021
 * @version : 1.0
 */
@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate date;

    //From the pupil module
    @Transient
    private Pupil pupil;
    private Long idPupil;

    //From the User module
    @Transient
    private String nameFirstnameUser;
    private Long idUser;

    //From the Finance module
    @Transient
    private Currency currency;
    private Long idCurrency;

    //From the Finance module
    @Transient
    private PaymentSystem paymentSystem;

    @Column(unique = true)
    private Long idPaymentSystem;

    @ManyToOne
    private Fees fees;

    //Attribute to allow removing an entity
    @Column(nullable = false)
    private boolean remove;
}
