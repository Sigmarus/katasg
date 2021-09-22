package com.katasg;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class OperationTest {
    
    @Test
    public void givenNotPositiveAmount_whenCreatingAnOperation_thenThrowAnInvalidOperationAmountException() {
        // deposit operation
        assertThrows(InvalidOperationAmountException.class, () -> {
            new Operation(OperationType.DEPOSIT, new BigDecimal(0));
        });
        assertThrows(InvalidOperationAmountException.class, () -> {
            new Operation(OperationType.DEPOSIT, new BigDecimal(-1));
        });
        assertThrows(InvalidOperationAmountException.class, () -> {
            new Operation(OperationType.DEPOSIT, null);
        });
        // withdrawal operation
        assertThrows(InvalidOperationAmountException.class, () -> {
            new Operation(OperationType.WITHDRAWAL, new BigDecimal(0));
        });
        assertThrows(InvalidOperationAmountException.class, () -> {
            new Operation(OperationType.WITHDRAWAL, new BigDecimal(-1));
        });
        assertThrows(InvalidOperationAmountException.class, () -> {
            new Operation(OperationType.WITHDRAWAL, null);
        });
    }
}
