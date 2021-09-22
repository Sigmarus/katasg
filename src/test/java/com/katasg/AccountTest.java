package com.katasg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
    }
    
    @Test
    public void givenNewAccount_whenAccountIsCreated_thenInitialAmountIsZero() {
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    public void givenNotPositiveOrNullAmount_whenDeposit_thenThrowInvalidOperationAmountException() {
        assertThrows(InvalidOperationAmountException.class, () -> {
            account.deposit(new BigDecimal(-100));
        });
        assertThrows(InvalidOperationAmountException.class, () -> {
            account.deposit(new BigDecimal(0));
        });
        assertThrows(InvalidOperationAmountException.class, () -> {
            account.deposit(null);
        });

        // also check that balance has remained unchanged
        assertEquals(0., account.getBalance().doubleValue());
    }

    @Test
    public void givenValidAmount_whenExecutingAnOperation_thenBalanceIsCreditedWithAmount() {
        try {
            account.deposit(new BigDecimal(100));
            account.withdraw(new BigDecimal(90));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(10., account.getBalance().doubleValue());
    }

    @Test
    public void givenValidAmount_whenExecutingAnOperation_thenNewOperationIsAddedToAccount() {
        try {
            account.deposit(new BigDecimal(100));
            account.withdraw(new BigDecimal(90));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(2, account.getOperations().size());
        assertEquals(OperationType.DEPOSIT, account.getOperations().get(0).getType());
        assertEquals(100., account.getOperations().get(0).getAmount().doubleValue());
        assertEquals(OperationType.WITHDRAWAL, account.getOperations().get(1).getType());
        assertEquals(90., account.getOperations().get(1).getAmount().doubleValue());
    }
}
