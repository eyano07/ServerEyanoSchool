package io.eyano.eyanoschool.feesservice.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents the TypeFees table in the database
 * @author : Pascal Tshingila
 * @since : 02/02/2021
 * @version : 1.0
 */
@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class TypeFees {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String designation;

    //Attribute to allow removing an entity
    @Column(nullable = false)
    private boolean remove;

}
