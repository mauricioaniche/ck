package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfNumbersTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/numbers");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("numbers.Numbers");

		Assert.assertEquals(5, a.getNumbersQty());

		Assert.assertEquals(5, a.getMethod("m1/0").get().getNumbersQty());
		Assert.assertEquals(0, a.getMethod("m2/0").get().getNumbersQty());

	}
}
