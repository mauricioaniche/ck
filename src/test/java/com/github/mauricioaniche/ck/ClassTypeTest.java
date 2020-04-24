package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ClassTypeTest extends BaseTest {

  private static Map<String, CKClassResult> report;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/class-types");
  }

  @Test
  public void identifyTypesCorrectly() {

    Assertions.assertEquals(5, report.size());

    CKClassResult a = report.get("classtypes.A");
    Assertions.assertEquals("class", a.getType());

    CKClassResult b = report.get("classtypes.A$B");
    Assertions.assertEquals("innerclass", b.getType());

    CKClassResult mathOperation = report.get("classtypes.MathOperation");
    Assertions.assertEquals("interface", mathOperation.getType());

    CKClassResult anon1 = report.get("classtypes.A$Anonymous1");
    Assertions.assertEquals("anonymous", anon1.getType());

    CKClassResult anon2 = report.get("classtypes.A$Anonymous2");
    Assertions.assertEquals("anonymous", anon2.getType());
  }
}
