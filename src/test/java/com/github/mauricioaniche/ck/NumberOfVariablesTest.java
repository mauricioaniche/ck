package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfVariablesTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/variables");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("variables.Variables");

		Assertions.assertEquals(8, a.getVariablesQty());

		Assertions.assertEquals(4, a.getMethod("m1/0").get().getVariablesQty());
		Assertions.assertEquals(4, a.getMethod("m2/0").get().getVariablesQty());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getVariablesQty());
		
		CKClassResult b = report.get("variables.RecordVariables");

		Assertions.assertEquals(4, b.getVariablesQty());

		Assertions.assertEquals(2, b.getMethod("actionPerformed/1[java.awt.event.ActionEvent]").get().getVariablesQty());
		Assertions.assertEquals(2, b.getMethod("privateStaticMethod/0").get().getVariablesQty());
		Assertions.assertEquals(0, b.getMethod("defaultMethod/0").get().getVariablesQty());
	}
}
