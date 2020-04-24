package com.github.mauricioaniche.ck;

import org.eclipse.jdt.core.dom.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

public class ModifiersTest extends BaseTest {

  private static Map<String, CKClassResult> report;
  private static Map<String, Integer> modifiersByMethod;
  private static CKClassResult classResult;

  @BeforeAll
  public static void setUp() {
    report = run(fixturesDir() + "/modifiers");
    classResult = report.get("modifiers.ClassWithModifiers");
    modifiersByMethod =
        classResult.getMethods().stream()
            .collect(Collectors.toMap(CKMethodResult::getMethodName, CKMethodResult::getModifiers));
  }

  @Test
  public void testClassIsAbstract() {
    Assertions.assertTrue(Modifier.isAbstract(classResult.getModifiers()));
  }

  @Test
  public void testFinalMethod() {
    Assertions.assertTrue(Modifier.isFinal(modifiersByMethod.get("finalMethod/0")));
  }

  @Test
  public void testAbstractMethod() {
    Assertions.assertTrue(Modifier.isAbstract(modifiersByMethod.get("abstractMethod/0")));
  }

  @Test
  public void testPrivateMethod() {
    Assertions.assertTrue(Modifier.isPrivate(modifiersByMethod.get("privateMethod/0")));
  }

  @Test
  public void testNativeMethod() {
    Assertions.assertTrue(Modifier.isNative(modifiersByMethod.get("nativeRun/0")));
  }
}
