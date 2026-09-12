package org.chamath.banking.model;

import org.chamath.banking.exception.InsufficientFundsException;
import org.chamath.banking.exception.NegativeValueException;
import org.chamath.banking.util.AccountNumberGenerator;

import java.math.BigDecimal;
import java.util.List;

public class CurrentAccount implements Account {
    private final String accountNumber;
    private final String accountType;
    private final Customer owner;
    private BigDecimal balance;
    private List<Transaction> transactionList;

    public CurrentAccount(Customer owner, double initialDeposit) throws NegativeValueException {
        this.accountNumber = AccountNumberGenerator.generate();
        this.accountType = "Current";
        this.owner = owner;
        this.balance = BigDecimal.valueOf(0);

        deposit(BigDecimal.valueOf(initialDeposit));
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
