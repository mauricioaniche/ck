package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class NOSITest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeAll
	public static void setUp() {
		report = run(fixturesDir() + "/nosi");
	}
	
	@Test
	public void staticInvocations() {
		CKClassResult a = report.get("nosi.Class2");
		Assertions.assertEquals(1, a.getNosi());
	}

	@Test
	public void staticInvocationsToMethodsInTheSameClass() {
		CKClassResult a = report.get("nosi.Class3");
		Assertions.assertEquals(2, a.getNosi());
	}

	@Test
	public void doesNotUnderstandWhenNotResolvable() {
		CKClassResult a = report.get("nosi.Class1");
		Assertions.assertEquals(0, a.getNosi());
	}
}
