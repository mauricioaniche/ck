package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.eclipse.jdt.core.dom.Modifier;
import java.util.Map;
import java.util.stream.Collectors;

public class ModifiersTest extends BaseTest {


	private static Map<String, CKClassResult> report;
	private static Map<String, Integer> modifiersByMethod;
	private static CKClassResult classResult;
	
	@BeforeClass
	public static void setUp() {
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
