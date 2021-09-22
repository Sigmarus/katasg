package com.katasg;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {
    private OperationType type;
    private BigDecimal amount;
    private Date date;

    public Operation(OperationType operationType, BigDecimal amount) throws InvalidOperationAmountException {
        if (amount == null || amount.signum() < 1) {
            throw new InvalidOperationAmountException();
        }
        this.type = operationType;
        this.amount = amount;
        this.date = new Date();
    }

    public OperationType getType() {
        return this.type;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Date getDate() {
        return this.date;
    }
}
