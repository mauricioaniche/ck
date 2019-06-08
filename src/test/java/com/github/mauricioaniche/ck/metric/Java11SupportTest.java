package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Java11SupportTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
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
