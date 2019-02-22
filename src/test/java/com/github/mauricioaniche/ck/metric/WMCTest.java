package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WMCTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/wmc");
	}

	@Test
	public void countAllBranchInstructions() {
		Assert.assertEquals(2, report.getByClassName("wmc.CC1").getWmc());
		Assert.assertEquals(3, report.getByClassName("wmc.CC2").getWmc());
		Assert.assertEquals(10, report.getByClassName("wmc.CC3").getWmc());
	}

	@Test
	public void methodLevel() {

		CKNumber a = report.getByClassName("wmc.CC1");
		Assert.assertEquals(1, a.getMethod("m1").get().getWmc());
		Assert.assertEquals(1, a.getMethod("m2").get().getWmc());

		CKNumber b = report.getByClassName("wmc.CC2");
		Assert.assertEquals(2, b.getMethod("m1").get().getWmc());
		Assert.assertEquals(1, b.getMethod("m2").get().getWmc());

		CKNumber c = report.getByClassName("wmc.CC3");
		Assert.assertEquals(10, c.getMethod("m1").get().getWmc());
	}
}
