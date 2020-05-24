package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JDTUtilsTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/jdt");
	}

	@Test
	public void methodNames() {
		CKClassResult a = report.get("jdt.Jdt1");

		Assertions.assertTrue(a.getMethod("m1/0").isPresent());
		Assertions.assertTrue(a.getMethod("m2/2[int,double]").isPresent());
		Assertions.assertTrue(a.getMethod("m3/3[int,jdt.A,double]").isPresent());
		Assertions.assertTrue(a.getMethod("m4/4[int,int[],java.lang.String[],jdt.A[]]").isPresent());
	}
}
