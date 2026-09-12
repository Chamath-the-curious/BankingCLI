package org.chamath.exception;

public class InsufficientFundsException extends Exception {
    @Override
    public String getMessage() {
        return "Insufficient funds";
    }
}
