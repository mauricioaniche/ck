package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfMaxNestedBlocksTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/nestedblocks");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks");

		Assertions.assertEquals(5, a.getMaxNestedBlocks());

		Assertions.assertEquals(5, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(2, a.getMethod("m2/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getMaxNestedBlocks());
	}

	@Test
	public void blocksWithNoClearOpenBracket() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks2");

		Assertions.assertEquals(5, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(2, a.getMethod("m2/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getMaxNestedBlocks());

		Assertions.assertEquals(5, a.getMaxNestedBlocks());
	}

	@Test
	public void loops() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks3");

		Assertions.assertEquals(5, a.getMethod("m1/0").get().getMaxNestedBlocks());

		Assertions.assertEquals(5, a.getMaxNestedBlocks());
	}

	@Test
	public void switchCases() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks4");

		Assertions.assertEquals(4, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(4, a.getMethod("m2/0").get().getMaxNestedBlocks());

		Assertions.assertEquals(4, a.getMaxNestedBlocks());
	}

	@Test
	public void blocksJustBecause() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks5");

		Assertions.assertEquals(3, a.getMethod("m1/0").get().getMaxNestedBlocks());

		Assertions.assertEquals(3, a.getMaxNestedBlocks());
	}

	@Test
	public void tryCatch() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks6");

		Assertions.assertEquals(3, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(6, a.getMethod("m2/0").get().getMaxNestedBlocks());


		Assertions.assertEquals(6, a.getMaxNestedBlocks());
	}

	@Test
	public void useSynchronized() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks7");

		Assertions.assertEquals(2, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(2, a.getMethod("m1/0").get().getMaxNestedBlocks());


		Assertions.assertEquals(2, a.getMaxNestedBlocks());
	}

	// Basic enums do not have nested blocks
	@Test
	public void enums() {
		CKClassResult a = report.get("nestedblocks.SimpleEnum");

		Assertions.assertEquals(0, a.getMaxNestedBlocks());
	}

	// based on issue #40
	@Test
	public void classesWithNoMethods() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks8");

		Assertions.assertEquals(0, a.getMaxNestedBlocks());
	}

	@Test
	public void nonBlockStatement() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks9");

		Assertions.assertEquals(2, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(2, a.getMethod("m2/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(2, a.getMethod("m3/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(2, a.getMethod("m4/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(3, a.getMethod("m5/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(3, a.getMethod("m6/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(3, a.getMaxNestedBlocks());
	}
}
