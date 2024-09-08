package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfLogStatementsTest extends BaseTest {

    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/logs");
    }

    @Test
    public void testCountLogStatementsFile() {
        CKClassResult a = report.get("logs.LogStatements");

        assertEquals(4, a.getNumberOfLogStatements());

        assertEquals(1, a.getMethod("m1/0").get().getLogStatementsQty());
        assertEquals(0, a.getMethod("m2/0").get().getLogStatementsQty());
        assertEquals(3, a.getMethod("m3/0").get().getLogStatementsQty());
    }

    @Test
    public void testCountLog4JHelloWorldFile() {
        CKClassResult a = report.get("logs.Log4JHelloWorld");
        assertEquals(7, a.getNumberOfLogStatements());
    }

    @Test
    public void testCountSLF4JFile() {
        CKClassResult a = report.get("logs.SLF4JExample");
        assertEquals(5, a.getNumberOfLogStatements());
    }
}
