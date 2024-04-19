package io.eyano.eyanoschool.feesservice.exceptions;
/**
 * This class allows you to handle the RemoveTrueException exception
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-18, Sun
 */
public class RemoveTrueException extends Exception{
    public RemoveTrueException(String message) {
        super(message);
    }
    public RemoveTrueException() {
        super("Remove must be false");
    }
}
