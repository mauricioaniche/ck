package com.github.mauricioaniche.ck;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.jdt.core.dom.Modifier;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModifiersTest extends BaseTest {

	private static Map<String, Integer> modifiersByMethod;
	private static CKClassResult classResult;
	
	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/modifiers");
		classResult = report.get("modifiers.ClassWithModifiers");
		modifiersByMethod = classResult.getMethods().stream().collect(Collectors.toMap(x -> x.getMethodName(), x->x.getModifiers()));
	}
	
	
	@Test
	public void testClassIsAbstract() {
		assertTrue(Modifier.isAbstract(classResult.getModifiers()));
	}
	
	@Test
	public void testFinalMethod() {
		assertTrue(Modifier.isFinal(modifiersByMethod.get("finalMethod/0")));
	}

	@Test
	public void testAbstractMethod() {
		assertTrue(Modifier.isAbstract(modifiersByMethod.get("abstractMethod/0")));
	}

	@Test
	public void testPrivateMethod() {
		assertTrue(Modifier.isPrivate(modifiersByMethod.get("privateMethod/0")));
	}

	@Test
	public void testNativeMethod() {
		assertTrue(Modifier.isNative(modifiersByMethod.get("nativeRun/0")));
	}

}
