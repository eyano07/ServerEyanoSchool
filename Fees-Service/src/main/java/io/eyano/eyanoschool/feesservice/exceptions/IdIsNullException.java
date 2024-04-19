package io.eyano.eyanoschool.feesservice.exceptions;

import lombok.extern.slf4j.Slf4j;
/**
 * This class allows you to handle the IdIsNullException exception
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Slf4j
public class IdIsNullException extends Exception{
    public IdIsNullException(String message) {
        super(message);
    }

    public IdIsNullException() {
        super("The id is null, please provide a valid id");
    }
}
