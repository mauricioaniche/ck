package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ParametersTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
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
