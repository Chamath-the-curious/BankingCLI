package org.chamath.banking.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private String Type;
    private BigDecimal amount;
    private Date date;
    private BigDecimal balanceAfterTransaction;
}
