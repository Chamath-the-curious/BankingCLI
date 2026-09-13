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

    public Transaction(BigDecimal balanceAfterTransaction, BigDecimal amount, TransactionType type, String transactionId) {
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.date = LocalDate.now();
        this.amount = amount;
        Type = type;
        this.transactionId = transactionId;
    }
}
