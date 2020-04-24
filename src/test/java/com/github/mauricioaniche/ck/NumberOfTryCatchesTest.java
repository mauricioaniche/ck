package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class NumberOfTryCatchesTest extends BaseTest {

  private static Map<String, CKClassResult> report;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/trycatch");
  }

  @Test
  public void count() {
    CKClassResult a = report.get("trycatch.TryCatch");

    Assertions.assertEquals(4, a.getTryCatchQty());

    Assertions.assertEquals(2, a.getMethod("m1/0").get().getTryCatchQty());
    Assertions.assertEquals(1, a.getMethod("m2/0").get().getTryCatchQty());
    Assertions.assertEquals(1, a.getMethod("m3/0").get().getTryCatchQty());
    Assertions.assertEquals(0, a.getMethod("m4/0").get().getTryCatchQty());
  }
}
