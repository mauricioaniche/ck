package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfParenthesisTest extends BaseTest{

    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/parenthesis");
    }

    @Test
    public void count() {
        CKClassResult a = report.get("parenthesis.NumberOfParenthesis");
        Assertions.assertEquals(2, a.getParenthesizedExpsQty());
        Assertions.assertEquals(2, a.getMethod("m1/0").get().getParenthesizedExpsQty());
    }
}
