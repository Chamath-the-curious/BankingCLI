package org.chamath.banking.exception;

public class CustomerNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Customer not found";
    }
}
