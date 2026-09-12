package org.chamath.banking.model;

import org.chamath.banking.exception.InsufficientFundsException;
import org.chamath.banking.exception.NegativeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CurrentsAccountTest {

    @Mock
    private Customer customer;

    private CurrentAccount currentAccount;

    @BeforeEach
    void setup() {
        currentAccount = new CurrentAccount(customer, 1000);
    }

    @Test
    void depositTest() throws NegativeValueException {
        currentAccount.deposit(BigDecimal.valueOf(500));
        assertEquals(currentAccount.getBalance(), BigDecimal.valueOf(1500.0));
    }

    @Test
    void depositNegativeAmountTest() {
        assertThrows(
                NegativeValueException.class,
                () -> currentAccount.deposit(BigDecimal.valueOf(-1000)));
    }

    @Test
    void withdrawTest() throws NegativeValueException, InsufficientFundsException {
        currentAccount.withdraw(BigDecimal.valueOf(500));
        assertEquals(currentAccount.getBalance(), BigDecimal.valueOf(500.0));
    }

    @Test
    void withdrawNegativeAmountTest() {
        assertThrows(
                NegativeValueException.class,
                () -> currentAccount.withdraw(BigDecimal.valueOf(-500)));
    }

    @Test
    void withdrawAmountHigherThanBalance() {
        assertThrows(
                InsufficientFundsException.class,
                () -> currentAccount.withdraw(BigDecimal.valueOf(2000)));
    }
}