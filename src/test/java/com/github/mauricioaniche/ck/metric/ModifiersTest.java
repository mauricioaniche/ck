package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;

import javassist.Modifier;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.stream.Collectors;

public class ModifiersTest extends BaseTest {


	private static Map<String, CKClassResult> report;
	static Map<String, Integer> modifiersByMethod ;
	private static CKClassResult a;
	
	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/modifiers");
		a = report.get("modifiers.ClassWithModifiers");
		modifiersByMethod = a.getMethods().stream().collect(Collectors.toMap(x -> x.getMethodName(), x->x.getModifiers()));
	}
	@Test
	public void testClassIsAbstract() {
		assertTrue(Modifier.isAbstract(a.getModifiers()));
	}
	
	@Test
	public void testFinalMethod() {
		assertTrue(Modifier.isFinal(modifiersByMethod.get("finalMethod/0")));
	}

}
