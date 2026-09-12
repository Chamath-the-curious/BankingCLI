package org.chamath.model;

import org.chamath.exception.InsufficientFundsException;
import org.chamath.exception.NegativeValueException;

import java.math.BigDecimal;

public interface Account {
    public void deposit(BigDecimal amount) throws NegativeValueException;
    public void withdraw(BigDecimal amount) throws NegativeValueException, InsufficientFundsException;
}
