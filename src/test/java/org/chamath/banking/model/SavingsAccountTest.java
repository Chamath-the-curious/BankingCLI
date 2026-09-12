package org.chamath.banking.model;

import org.chamath.banking.exception.InsufficientFundsException;
import org.chamath.banking.exception.NegativeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SavingsAccountTest {

    @Mock
    private Customer customer;

    private SavingsAccount savingsAccount;

    @BeforeEach
    void setup() {
        savingsAccount = new SavingsAccount(customer, 1000);
    }

    @Test
    void depositTest() throws NegativeValueException {
        savingsAccount.deposit(BigDecimal.valueOf(500));
        assertEquals(savingsAccount.getBalance(), BigDecimal.valueOf(1500.0));
    }

    @Test
    void depositNegativeAmountTest() {
        assertThrows(
                NegativeValueException.class,
                () -> savingsAccount.deposit(BigDecimal.valueOf(-1000)));
    }

    @Test
    void withdrawTest() throws NegativeValueException, InsufficientFundsException {
        savingsAccount.withdraw(BigDecimal.valueOf(500));
        assertEquals(savingsAccount.getBalance(), BigDecimal.valueOf(500.0));
    }

    @Test
    void withdrawNegativeAmountTest() {
        assertThrows(
                NegativeValueException.class,
                () -> savingsAccount.withdraw(BigDecimal.valueOf(-500)));
    }

    @Test
    void withdrawAmountHigherThanBalance() {
        assertThrows(
                InsufficientFundsException.class,
                () -> savingsAccount.withdraw(BigDecimal.valueOf(2000)));
    }
}