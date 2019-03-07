package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfMathOperationsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/math");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("math.Math");

		Assert.assertEquals(5, a.getMathOperationsQty());

		Assert.assertEquals(2, a.getMethod("m1/0").get().getMathOperationsQty());
		Assert.assertEquals(3, a.getMethod("m2/0").get().getMathOperationsQty());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getMathOperationsQty());

	}
}
