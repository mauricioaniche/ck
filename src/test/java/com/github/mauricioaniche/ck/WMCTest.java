package com.github.mauricioaniche.ck;

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
	public void someOldRandomTests() {

		CKClassResult a = report.get("wmc.CC1");
		CKClassResult b = report.get("wmc.CC2");
		CKClassResult c = report.get("wmc.CC3");

		Assert.assertEquals(4, a.getWmc());
		Assert.assertEquals(6, b.getWmc());
		Assert.assertEquals(11, c.getWmc());

		Assert.assertEquals(2, a.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(2, a.getMethod("m2/0").get().getWmc());
		Assert.assertEquals(3, b.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(3, b.getMethod("m2/0").get().getWmc());
		Assert.assertEquals(11, c.getMethod("m1/0").get().getWmc());
	}

	@Test
	public void doNotCountSubClassesCode() {
		CKClassResult c = report.get("wmc.CC4");
		Assert.assertEquals(11, c.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(11, c.getWmc());

		CKClassResult c2 = report.get("wmc.CC4$1MethodInner");
		Assert.assertEquals(3, c2.getMethod("print/0").get().getWmc());
	}

	// related to issue #31
	// note that, although m1 and m2 are semantically similar, their WMC are currently differently.
	// it would need some smarter analysis to figure out that the complexity of the boolean variable was
	// already measured before.
	@Test
	public void inlineConditions() {
		CKClassResult c = report.get("wmc.CC6");
		Assert.assertEquals(7, c.getWmc());

		Assert.assertEquals(3, c.getMethod("m1/1[int]").get().getWmc());
		Assert.assertEquals(4, c.getMethod("m2/1[int]").get().getWmc());
	}

	@Test
	public void straightBooleanComparison() {
		CKClassResult c = report.get("wmc.CC7");

		Assert.assertEquals(2, c.getMethod("m2/1[boolean]").get().getWmc());
		Assert.assertEquals(2, c.getMethod("m1/1[boolean]").get().getWmc());
	}

	@Test
	public void loops() {

		CKClassResult c = report.get("wmc.CC8");

		Assert.assertEquals(3, c.getMethod("m0/0").get().getWmc());
		Assert.assertEquals(6, c.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(6, c.getMethod("m2/0").get().getWmc());
		Assert.assertEquals(6, c.getMethod("m3/0").get().getWmc());
		Assert.assertEquals(2, c.getMethod("m4/0").get().getWmc());
		Assert.assertEquals(3, c.getMethod("m5/0").get().getWmc());
		Assert.assertEquals(6, c.getMethod("m6/0").get().getWmc());

		Assert.assertEquals(3+6+6+6+2+3+6, c.getWmc());

	}

	@Test
	public void ifs() {

		CKClassResult c = report.get("wmc.CC9");

		Assert.assertEquals(1, c.getMethod("m0/0").get().getWmc());
		Assert.assertEquals(2, c.getMethod("m1/0").get().getWmc());
		Assert.assertEquals(3, c.getMethod("m2/0").get().getWmc());
		Assert.assertEquals(2, c.getMethod("m3/0").get().getWmc());
		Assert.assertEquals(4, c.getMethod("m4/0").get().getWmc());
		Assert.assertEquals(8, c.getMethod("m5/0").get().getWmc());
		Assert.assertEquals(9, c.getMethod("m6/0").get().getWmc());
		Assert.assertEquals(5, c.getMethod("m7/0").get().getWmc());
		Assert.assertEquals(4, c.getMethod("m8/0").get().getWmc());
		Assert.assertEquals(8, c.getMethod("m9/0").get().getWmc());

		// the final 2 is for the two other util methods we have there
		Assert.assertEquals(1+2+3+2+4+8+9+5+4+8 + 2, c.getWmc());

	}

	@Test
	public void nested() {
		CKClassResult c = report.get("wmc.CC10");
		Assert.assertEquals(9, c.getMethod("m2/0").get().getWmc());
		Assert.assertEquals(12, c.getMethod("m1/0").get().getWmc());
	}
}
