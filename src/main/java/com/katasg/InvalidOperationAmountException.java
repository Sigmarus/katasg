package com.katasg;

public class InvalidOperationAmountException extends Exception {

    public InvalidOperationAmountException() {
        super("Operation amount must be a positive number with a 2-digits decimal precision.");
    }
}