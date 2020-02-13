package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfLoopsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/loop");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("loop.Loop");

		Assert.assertEquals(5, a.getLoopQty());

		Assert.assertEquals(1, a.getMethod("m1/0").get().getLoopQty());
		Assert.assertEquals(2, a.getMethod("m2/0").get().getLoopQty());
		Assert.assertEquals(1, a.getMethod("m3/0").get().getLoopQty());
		Assert.assertEquals(0, a.getMethod("m4/0").get().getLoopQty());
		Assert.assertEquals(1, a.getMethod("m5/0").get().getLoopQty());

	}

	@Test
	public void infiniteLoops() {
		CKClassResult a = report.get("loop.Loop2");

		Assert.assertEquals(6, a.getLoopQty());
		Assert.assertEquals(0, a.getMethod("m0/0").get().getLoopQty());
		Assert.assertEquals(2, a.getMethod("m1/0").get().getLoopQty());
		Assert.assertEquals(1, a.getMethod("m2/0").get().getLoopQty());
		Assert.assertEquals(2, a.getMethod("m3/0").get().getLoopQty());
		Assert.assertEquals(1, a.getMethod("m4/0").get().getLoopQty());

	}
}
