package io.eyano.eyanoschool.feesservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
/**
 * This is a class which represents an external entity coming from the Utility-Service module
 * @author : Pascal
 * @since : 02/02/2021
 * @version : 1.0
 */
@Data @Builder @ToString
public class SchoolYear {
    private Long id;
    private String designation;
    private LocalDate startDate;
    private LocalDate endDAte;
    private boolean isCurrent;
}
