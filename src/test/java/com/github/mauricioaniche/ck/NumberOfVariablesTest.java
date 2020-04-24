package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class NumberOfVariablesTest extends BaseTest {

  private static Map<String, CKClassResult> report;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/variables");
  }

  @Test
  public void count() {
    CKClassResult a = report.get("variables.Variables");

    Assertions.assertEquals(8, a.getVariablesQty());

    Assertions.assertEquals(4, a.getMethod("m1/0").get().getVariablesQty());
    Assertions.assertEquals(4, a.getMethod("m2/0").get().getVariablesQty());
    Assertions.assertEquals(0, a.getMethod("m3/0").get().getVariablesQty());
  }
}
