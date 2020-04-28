package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Java11SupportTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeAll
	public static void setUp() {
		report = run(fixturesDir() + "/java11");
	}

	@Test
	public void supportsJava11() {
		assertTrue(this.report.containsKey("java11.Java11"));
		CKClassResult clazz = this.report.get("java11.Java11");
		Optional<CKMethodResult> maybeMethod = clazz.getMethod("m1/0");
		assertTrue(maybeMethod.isPresent());
		CKMethodResult method = maybeMethod.get();
		assertEquals(1, method.getLambdasQty());
	}

}
