package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class NumberOfReturnsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/returns");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("returns.Returns");

		Assert.assertEquals(6, a.getReturnQty());

		Assert.assertEquals(3, a.getMethod("m1/0").get().getReturnQty());
		Assert.assertEquals(2, a.getMethod("m2/0").get().getReturnQty());
		Assert.assertEquals(1, a.getMethod("m3/0").get().getReturnQty());
		Assert.assertEquals(0, a.getMethod("m4/0").get().getReturnQty());

	}
}
