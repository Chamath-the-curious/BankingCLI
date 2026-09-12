package org.chamath.banking.exception;

public class InsufficientFundsException extends Exception {
    @Override
    public String getMessage() {
        return "Insufficient funds";
    }
}
