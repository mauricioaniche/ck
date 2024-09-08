package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfLoopsTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/loop");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("loop.Loop");

		Assertions.assertEquals(5, a.getLoopQty());

		Assertions.assertEquals(1, a.getMethod("m1/0").get().getLoopQty());
		Assertions.assertEquals(2, a.getMethod("m2/0").get().getLoopQty());
		Assertions.assertEquals(1, a.getMethod("m3/0").get().getLoopQty());
		Assertions.assertEquals(0, a.getMethod("m4/0").get().getLoopQty());
		Assertions.assertEquals(1, a.getMethod("m5/0").get().getLoopQty());

	}

	@Test
	public void infiniteLoops() {
		CKClassResult a = report.get("loop.Loop2");

		Assertions.assertEquals(6, a.getLoopQty());
		Assertions.assertEquals(0, a.getMethod("m0/0").get().getLoopQty());
		Assertions.assertEquals(2, a.getMethod("m1/0").get().getLoopQty());
		Assertions.assertEquals(1, a.getMethod("m2/0").get().getLoopQty());
		Assertions.assertEquals(2, a.getMethod("m3/0").get().getLoopQty());
		Assertions.assertEquals(1, a.getMethod("m4/0").get().getLoopQty());

	}
}
