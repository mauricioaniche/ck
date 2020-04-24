package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ParametersTest extends BaseTest {

  private static Map<String, CKClassResult> report;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/parameters");
  }

  @Test
  public void testSignatureResolution() {
    Assertions.assertTrue(report.containsKey("A"));
    CKClassResult clazz = report.get("A");
    Assertions.assertTrue(
        clazz
            .getMethod("m1/6[pac.B,pac.B,Missing,pac.Missing,Missing2,pacmissing.Missing2]")
            .isPresent());
  }
}
