package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class NumberOfMathOperationsTest extends BaseTest {

  private static Map<String, CKClassResult> report;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/math");
  }

  @Test
  public void count() {
    CKClassResult a = report.get("math.Math");

    Assertions.assertEquals(5, a.getMathOperationsQty());

    Assertions.assertEquals(2, a.getMethod("m1/0").get().getMathOperationsQty());
    Assertions.assertEquals(3, a.getMethod("m2/0").get().getMathOperationsQty());
    Assertions.assertEquals(0, a.getMethod("m3/0").get().getMathOperationsQty());
  }
}
