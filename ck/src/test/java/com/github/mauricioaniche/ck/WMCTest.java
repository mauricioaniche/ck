package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WMCTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/wmc");
	}

	@Test
	public void someOldRandomTests() {

		CKClassResult a = report.get("wmc.CC1");
		CKClassResult b = report.get("wmc.CC2");
		CKClassResult c = report.get("wmc.CC3");

		Assertions.assertEquals(7, a.getWmc());
		Assertions.assertEquals(6, b.getWmc());
		Assertions.assertEquals(11, c.getWmc());

		Assertions.assertEquals(2, a.getMethod("m1/0").get().getWmc());
		Assertions.assertEquals(2, a.getMethod("m2/0").get().getWmc());
		Assertions.assertEquals(3, a.getMethod("m3/0").get().getWmc());
		Assertions.assertEquals(3, b.getMethod("m1/0").get().getWmc());
		Assertions.assertEquals(3, b.getMethod("m2/0").get().getWmc());
		Assertions.assertEquals(11, c.getMethod("m1/0").get().getWmc());
	}

	@Test
	public void doNotCountSubClassesCode() {
		CKClassResult c = report.get("wmc.CC4");
		Assertions.assertEquals(11, c.getMethod("m1/0").get().getWmc());
		Assertions.assertEquals(11, c.getWmc());

		CKClassResult c2 = report.get("wmc.CC4$1MethodInner");
		Assertions.assertEquals(3, c2.getMethod("print/0").get().getWmc());
	}

	// related to issue #31
	// note that, although m1 and m2 are semantically similar, their WMC are currently differently.
	// it would need some smarter analysis to figure out that the complexity of the boolean variable was
	// already measured before.
	@Test
	public void inlineConditions() {
		CKClassResult c = report.get("wmc.CC6");
		Assertions.assertEquals(7, c.getWmc());

		Assertions.assertEquals(3, c.getMethod("m1/1[int]").get().getWmc());
		Assertions.assertEquals(4, c.getMethod("m2/1[int]").get().getWmc());
	}

	@Test
	public void straightBooleanComparison() {
		CKClassResult c = report.get("wmc.CC7");

		Assertions.assertEquals(2, c.getMethod("m2/1[boolean]").get().getWmc());
		Assertions.assertEquals(2, c.getMethod("m1/1[boolean]").get().getWmc());
	}

	@Test
	public void loops() {

		CKClassResult c = report.get("wmc.CC8");

		Assertions.assertEquals(3, c.getMethod("m0/0").get().getWmc());
		Assertions.assertEquals(6, c.getMethod("m1/0").get().getWmc());
		Assertions.assertEquals(6, c.getMethod("m2/0").get().getWmc());
		Assertions.assertEquals(6, c.getMethod("m3/0").get().getWmc());
		Assertions.assertEquals(2, c.getMethod("m4/0").get().getWmc());
		Assertions.assertEquals(3, c.getMethod("m5/0").get().getWmc());
		Assertions.assertEquals(6, c.getMethod("m6/0").get().getWmc());

		Assertions.assertEquals(3+6+6+6+2+3+6, c.getWmc());

	}

	@Test
	public void ifs() {

		CKClassResult c = report.get("wmc.CC9");

		Assertions.assertEquals(1, c.getMethod("m0/0").get().getWmc());
		Assertions.assertEquals(2, c.getMethod("m1/0").get().getWmc());
		Assertions.assertEquals(3, c.getMethod("m2/0").get().getWmc());
		Assertions.assertEquals(2, c.getMethod("m3/0").get().getWmc());
		Assertions.assertEquals(4, c.getMethod("m4/0").get().getWmc());
		Assertions.assertEquals(8, c.getMethod("m5/0").get().getWmc());
		Assertions.assertEquals(9, c.getMethod("m6/0").get().getWmc());
		Assertions.assertEquals(5, c.getMethod("m7/0").get().getWmc());
		Assertions.assertEquals(4, c.getMethod("m8/0").get().getWmc());
		Assertions.assertEquals(8, c.getMethod("m9/0").get().getWmc());

		// the final 2 is for the two other util methods we have there
		Assertions.assertEquals(1+2+3+2+4+8+9+5+4+8 + 2, c.getWmc());

	}

	@Test
	public void nested() {
		CKClassResult c = report.get("wmc.CC10");
		Assertions.assertEquals(9, c.getMethod("m2/0").get().getWmc());
		Assertions.assertEquals(12, c.getMethod("m1/0").get().getWmc());
	}
}
