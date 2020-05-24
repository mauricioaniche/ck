package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfComparisonsTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/comparison");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("comparison.Comparison");

		Assertions.assertEquals(4, a.getComparisonsQty());

		Assertions.assertEquals(2, a.getMethod("m1/0").get().getComparisonsQty());
		Assertions.assertEquals(1, a.getMethod("m2/0").get().getComparisonsQty());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getComparisonsQty());
		Assertions.assertEquals(1, a.getMethod("m4/0").get().getComparisonsQty());

	}
}
