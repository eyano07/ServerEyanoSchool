package io.eyano.eyanoschool.feesservice.exceptions;

/**
 * This class allows you to handle the IdNotNullException exception
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
public class IdNotNullException extends Exception{
    public IdNotNullException(String message) {
        super(message);
    }

    public IdNotNullException() {
        super("Identifier must be null");
    }
}
