package com.katasg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StatementPrinterTest {
    
    private static Account account;
    private static File outputFile;

    @BeforeAll
    public static void setUp() {
        account = new Account();
        try {
            account.deposit(new BigDecimal(10));
            account.deposit(new BigDecimal(15));
            account.withdraw(new BigDecimal(10));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            Path temp = Files.createTempFile("temp", ".txt");
            outputFile = temp.toFile();
        }
        catch (IOException e) {
            fail(e.getMessage());
        }    
    }

    @Test
    public void givenOutputAndAccount_whenCheckingOperations_thenPrintOperationsOnOutput() {
        // print operation to output stream
        try (FileOutputStream outputStream = new FileOutputStream(outputFile);
             FileInputStream inputStream = new FileInputStream(outputFile))
        {
            PrintStream out = new PrintStream(outputStream);
            StatementPrinter.printOperations(out, account);
        }
        catch (IOException e) {
            fail(e.getMessage());
        }

        // read result
        StringBuilder result = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(outputFile.getAbsolutePath()), StandardCharsets.UTF_8)) {
            stream.forEach(line -> result.append(line).append(System.lineSeparator()));
        }
        catch (IOException e) {
            fail(e.getMessage());
        }

        String expectedResult = new StringBuilder()
            .append("DATE | OPERATION | AMOUNT")
            .append(System.lineSeparator())
            .append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" | DEPOSIT | 10.0")
            .append(System.lineSeparator())
            .append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" | DEPOSIT | 15.0")
            .append(System.lineSeparator())
            .append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" | WITHDRAWAL | 10.0")
            .append(System.lineSeparator())
            .append("-------------------------")
            .append(System.lineSeparator())
            .append("CURRENT BALANCE : 15.0")
            .append(System.lineSeparator())
            .toString();

        assertEquals(expectedResult, result.toString());
    }
}
