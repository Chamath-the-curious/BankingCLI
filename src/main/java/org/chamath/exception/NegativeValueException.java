package org.chamath.exception;

public class NegativeValueException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid amount";
    }
}
