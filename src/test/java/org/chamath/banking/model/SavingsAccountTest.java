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
        try {
            savingsAccount = new SavingsAccount(customer, 1000);
        } catch (NegativeValueException e) {
            throw new RuntimeException(e);
        }
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
                () -> savingsAccount.deposit(BigDecimal.valueOf(-1000))
        );
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
                () -> savingsAccount.withdraw(BigDecimal.valueOf(-500))
        );
    }

    @Test
    void withdrawAmountHigherThanBalance() {
        assertThrows(
                InsufficientFundsException.class,
                () -> savingsAccount.withdraw(BigDecimal.valueOf(2000))
        );
    }

    @Test
    void transferTest() throws NegativeValueException, InsufficientFundsException{
        Account transferTo = new SavingsAccount(customer, 3000);

        savingsAccount.transfer(transferTo, BigDecimal.valueOf(750));
        assertEquals(savingsAccount.getBalance(), BigDecimal.valueOf(250.0));
        assertEquals(transferTo.getBalance(), BigDecimal.valueOf(3750.0));
    }

    @Test
    void transferNegativeAmount() throws NegativeValueException {
        Account transferTo = new SavingsAccount(customer, 3000);

        assertThrows(
                NegativeValueException.class,
                () -> savingsAccount.transfer(transferTo, BigDecimal.valueOf(-750))
        );
        assertEquals(savingsAccount.getBalance(), BigDecimal.valueOf(1000.0));
        assertEquals(transferTo.getBalance(), BigDecimal.valueOf(3000.0));
    }

    @Test
    void transferInsufficientAmount() throws NegativeValueException {
        Account transferTo = new SavingsAccount(customer, 3000);

        assertThrows(
                InsufficientFundsException.class,
                () -> savingsAccount.transfer(transferTo, BigDecimal.valueOf(5000))
        );
        assertEquals(savingsAccount.getBalance(), BigDecimal.valueOf(1000.0));
        assertEquals(transferTo.getBalance(), BigDecimal.valueOf(3000.0));
    }
}