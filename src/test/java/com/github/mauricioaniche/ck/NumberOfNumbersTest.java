package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfNumbersTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/numbers");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("numbers.Numbers");

		Assertions.assertEquals(5, a.getNumbersQty());

		Assertions.assertEquals(5, a.getMethod("m1/0").get().getNumbersQty());
		Assertions.assertEquals(0, a.getMethod("m2/0").get().getNumbersQty());

	}
}
