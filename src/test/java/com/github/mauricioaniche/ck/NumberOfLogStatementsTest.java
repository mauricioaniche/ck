package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class NumberOfLogStatementsTest extends BaseTest {

  private static Map<String, CKClassResult> report;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/logs");
  }

  @Test
  public void count() {
    CKClassResult a = report.get("logs.LogStatements");

    Assertions.assertEquals(4, a.getNumberOfLogStatements());

    Assertions.assertEquals(1, a.getMethod("m1/0").get().getLogStatementsQty());
    Assertions.assertEquals(0, a.getMethod("m2/0").get().getLogStatementsQty());
    Assertions.assertEquals(3, a.getMethod("m3/0").get().getLogStatementsQty());
  }
}
