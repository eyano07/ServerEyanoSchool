package io.eyano.eyanoschool.feesservice.entities;

import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
/**
 * This class represents the PaymentRegistration table in the database
 * @author : Pascal Tshingila
 * @since : 02/02/2021
 * @version : 1.0
 */
@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class PaymentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double amount;

    //From the Finance module
    @Transient
    private PaymentSystem paymentSystem;
    @Column(unique = true)
    private Long idPaymentSystem;

    //From the User module
    @Transient
    private String nameFirstnameUser;
    private Long idUser;

    //From the Registration module
    @Transient
    private String nameFirstnameCandidate;
    private Long idCandidate;

    @ManyToOne
    private Fees fees;

    //Attribute to allow removing an entity
    @Column(nullable = false)
    private boolean remove;
}
