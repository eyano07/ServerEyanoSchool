package io.eyano.eyanoschool.feesservice.entities;

import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents the FeesAllocation table in the database
 * @author : Pascal Tshingila
 * @since : 02/02/2021
 * @version : 1.0
 */
@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class FeesAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String designation;

    @Column(nullable = false)
    private double percentage;

    //From the utility module
    @Transient
    private SchoolYear schoolYear;
    @Column(nullable = false)
    private Long idSchoolYear;

    @ManyToOne
    private TypeFees typeFees;

    //Attribute to allow removing an entity
    @Column(nullable = false)
    private boolean remove;
}
