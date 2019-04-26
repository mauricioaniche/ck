package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class JDTUtilsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/jdt");
	}

	@Test
	public void methodNames() {
		CKClassResult a = report.get("jdt.Jdt1");

		Assert.assertTrue(a.getMethod("m1/0").isPresent());
		Assert.assertTrue(a.getMethod("m2/2[int,double]").isPresent());
		Assert.assertTrue(a.getMethod("m3/3[int,jdt.A,double]").isPresent());
		Assert.assertTrue(a.getMethod("m4/4[int,int[],java.lang.String[],jdt.A[]]").isPresent());
	}
}
