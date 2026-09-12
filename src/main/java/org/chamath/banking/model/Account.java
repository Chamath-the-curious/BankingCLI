package org.chamath.banking.model;

import org.chamath.banking.exception.InsufficientFundsException;
import org.chamath.banking.exception.NegativeValueException;

import java.math.BigDecimal;

public interface Account {
    public void deposit(BigDecimal amount) throws NegativeValueException;
    public void withdraw(BigDecimal amount) throws NegativeValueException, InsufficientFundsException;
}
