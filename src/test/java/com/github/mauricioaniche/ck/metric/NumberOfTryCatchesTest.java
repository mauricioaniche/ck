package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfTryCatchesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/trycatch");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("trycatch.TryCatch");

		Assert.assertEquals(4, a.getTryCatchQty());

		Assert.assertEquals(2, a.getMethod("m1/0").get().getTryCatchQty());
		Assert.assertEquals(1, a.getMethod("m2/0").get().getTryCatchQty());
		Assert.assertEquals(1, a.getMethod("m3/0").get().getTryCatchQty());
		Assert.assertEquals(0, a.getMethod("m4/0").get().getTryCatchQty());

	}
}
