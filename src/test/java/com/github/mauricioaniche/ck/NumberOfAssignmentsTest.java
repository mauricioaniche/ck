package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfAssignmentsTest extends BaseTest {

	private Map<String, CKClassResult> report;

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/assignments");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("assignments.Assignments");

		Assertions.assertEquals(8, a.getAssignmentsQty());

		Assertions.assertEquals(5, a.getMethod("m1/0").get().getAssignmentsQty());
		Assertions.assertEquals(3, a.getMethod("m2/0").get().getAssignmentsQty());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getAssignmentsQty());

	}
}
