package com.katasg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private BigDecimal balance;
    private List<Operation> operations;
    
    public Account() {
        this.balance = BigDecimal.ZERO;
        this.operations = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public void deposit(final BigDecimal amount) throws InvalidOperationAmountException {
        Operation operation = new Operation(OperationType.DEPOSIT, amount);
        this.operations.add(operation);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(final BigDecimal amount) throws InvalidOperationAmountException {
        Operation operation = new Operation(OperationType.WITHDRAWAL, amount);
        this.operations.add(operation);
        this.balance = this.balance.subtract(amount);
    }
}
