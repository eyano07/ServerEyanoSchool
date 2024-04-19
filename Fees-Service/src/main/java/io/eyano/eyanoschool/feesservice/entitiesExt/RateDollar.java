package io.eyano.eyanoschool.feesservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
/**
 * This is a class which represents an external entity coming from the Financial-Service module
 * @author : Pascal
 * @since : 02/02/2021
 * @version : 1.0
 */
@Data @Builder @ToString
public class RateDollar {
    private Long id;
    private String observation;
    private LocalDate date;
    private boolean isCurrentRate;
    private double amount;

}
