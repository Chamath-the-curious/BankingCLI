package org.chamath.banking.model;

import org.chamath.banking.exception.InsufficientFundsException;
import org.chamath.banking.exception.NegativeValueException;
import org.chamath.banking.util.AccountNumberGenerator;

import java.math.BigDecimal;

public class SavingsAccount implements Account {
    private final String accountNumber;
    private final String accountType;
    private final Customer owner;
    private BigDecimal balance;

    public SavingsAccount(Customer owner, double initialDeposit) {
        this.accountNumber = AccountNumberGenerator.generate();
        this.accountType = "Savings";
        this.owner = owner;
        this.balance = BigDecimal.valueOf(0);

        try {
            deposit(BigDecimal.valueOf(initialDeposit));
        } catch (NegativeValueException e) {
            throw new RuntimeException(e);
        }
    }

    public void deposit(BigDecimal amount) throws NegativeValueException {
        if (amount.compareTo(BigDecimal.valueOf(0)) >= 0) {
            balance = balance.add(amount);
        } else {
            throw new NegativeValueException();
        }
    }

    public void withdraw(BigDecimal amount) throws NegativeValueException, InsufficientFundsException {
        if (amount.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new NegativeValueException();
        }

        if (amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new InsufficientFundsException();
        }
    }

    public void transfer(Account transferTo, BigDecimal amount) throws NegativeValueException, InsufficientFundsException{
        withdraw(amount);
        transferTo.deposit(amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "\nAccount Number: " + accountNumber +
                "\nType         : " + accountType +
                "\nOwner        : " + owner +
                "\nBalance      : " + balance;
    }
}
