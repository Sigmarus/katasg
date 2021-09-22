package com.katasg;

import java.io.PrintStream;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

public class StatementPrinter {
    
    public static void printOperations(final PrintStream out, final Account account) {
        StringBuilder stringBuilder = new StringBuilder()
            .append("DATE | OPERATION | AMOUNT")
            .append(System.lineSeparator());
        
        account.getOperations().forEach(operation -> {
            stringBuilder.append(new SimpleDateFormat("yyyy-MM-dd").format(operation.getDate())).append(" | ")
            .append(operation.getType()).append(" | ")
            .append(operation.getAmount().setScale(2, RoundingMode.HALF_EVEN).doubleValue())
            .append(System.lineSeparator());
        });

        stringBuilder.append("-------------------------")
            .append(System.lineSeparator())
            .append("CURRENT BALANCE : ")
            .append(account.getBalance().setScale(2, RoundingMode.HALF_EVEN).doubleValue())
            .append(System.lineSeparator());

        out.print(stringBuilder.toString());
    }
}
