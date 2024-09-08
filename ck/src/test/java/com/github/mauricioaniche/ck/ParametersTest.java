package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParametersTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/parameters");
	}

	@Test
	public void testSignatureResolution() {
		assertTrue(this.report.containsKey("A"));
		CKClassResult clazz = this.report.get("A");
		assertTrue(clazz.getMethod("m1/6[pac.B,pac.B,Missing,pac.Missing,Missing2,pacmissing.Missing2]").isPresent());
	}

}
