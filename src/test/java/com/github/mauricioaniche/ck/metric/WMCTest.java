package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class WMCTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/wmc");
	}

	@Test
	public void countAllBranchInstructions() {
		Assert.assertEquals(4, report.get("wmc.CC1").getWmc());
		Assert.assertEquals(5, report.get("wmc.CC2").getWmc());
		Assert.assertEquals(11, report.get("wmc.CC3").getWmc());
	}

	@Test
	public void methodLevel() {

		CKClassResult a = report.get("wmc.CC1");
		Assert.assertEquals(2, a.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(2, a.getMethod("m2/0").get().getWmc());

		CKClassResult b = report.get("wmc.CC2");
		Assert.assertEquals(3, b.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(2, b.getMethod("m2/0").get().getWmc());

		CKClassResult c = report.get("wmc.CC3");
		Assert.assertEquals(11, c.getMethod("m1/0").get().getWmc());
	}
}
