package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParametersTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeAll
	public static void setUp() {
		report = run(fixturesDir() + "/parameters");
	}

	@Test
	public void testSignatureResolution() {
		assertTrue(this.report.containsKey("A"));
		CKClassResult clazz = this.report.get("A");
		assertTrue(clazz.getMethod("m1/6[pac.B,pac.B,Missing,pac.Missing,Missing2,pacmissing.Missing2]").isPresent());
	}

}
