package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.ASTDebugger;
import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class NumberOfVariablesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/variables");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("variables.Variables");

		Assert.assertEquals(8, a.getVariablesQty());

		Assert.assertEquals(4, a.getMethod("m1/0").get().getVariablesQty());
		Assert.assertEquals(4, a.getMethod("m2/0").get().getVariablesQty());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getVariablesQty());

	}
}
