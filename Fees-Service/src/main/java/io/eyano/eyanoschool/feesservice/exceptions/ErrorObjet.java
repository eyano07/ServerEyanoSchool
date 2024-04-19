package io.eyano.eyanoschool.feesservice.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
/**
 * this class allows you to store all Exceptions, format them and then send them to the client
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Data @Builder
public class ErrorObjet {
    private Integer statusCode;
    private Integer errorCode;
    private String message;
    private LocalDate date;

}
