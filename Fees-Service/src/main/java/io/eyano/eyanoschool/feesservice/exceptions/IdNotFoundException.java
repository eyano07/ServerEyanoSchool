package io.eyano.eyanoschool.feesservice.exceptions;

import lombok.extern.slf4j.Slf4j;
/**
 * This class allows you to handle the IdNotFoundException exception
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
@Slf4j
public class IdNotFoundException extends Exception {
    public IdNotFoundException() {
        super("Identifier not found in the database");
    }
    public IdNotFoundException(String message) {
        super(message);
    }
}
