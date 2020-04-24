package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

public class Java11SupportTest extends BaseTest {

  private static Map<String, CKClassResult> report;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/java11");
  }

  @Test
  public void supportsJava11() {
    Assertions.assertTrue(report.containsKey("java11.Java11"));
    CKClassResult clazz = report.get("java11.Java11");
    Optional<CKMethodResult> maybeMethod = clazz.getMethod("m1/0");
    Assertions.assertTrue(maybeMethod.isPresent());
    CKMethodResult method = maybeMethod.get();
    Assertions.assertEquals(1, method.getLambdasQty());
  }
}
