package io.eyano.eyanoschool.feesservice.entitiesExt;

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
public class Currency {
    private Long id;
    private String designation;
    private String symbol;
    private RateDollar rate;
}
