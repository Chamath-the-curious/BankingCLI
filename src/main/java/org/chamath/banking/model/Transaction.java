package org.chamath.banking.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private TransactionType Type;
    private BigDecimal amount;
    private LocalDate date;
    private BigDecimal balanceAfterTransaction;

    public Transaction(String transactionId, TransactionType type, BigDecimal amount, BigDecimal balanceAfterTransaction) {
        this.transactionId = transactionId;
        Type = type;
        this.amount = amount;
        this.date = LocalDate.now();
        this.balanceAfterTransaction = balanceAfterTransaction;
    }
}
