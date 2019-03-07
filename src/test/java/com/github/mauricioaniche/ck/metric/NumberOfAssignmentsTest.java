package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfAssignmentsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/assignments");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("assignments.Assignments");

		Assert.assertEquals(8, a.getAssignmentsQty());

		Assert.assertEquals(5, a.getMethod("m1/0").get().getAssignmentsQty());
		Assert.assertEquals(3, a.getMethod("m2/0").get().getAssignmentsQty());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getAssignmentsQty());

	}
}
