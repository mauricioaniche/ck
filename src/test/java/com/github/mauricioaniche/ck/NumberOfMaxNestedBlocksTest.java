package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfMaxNestedBlocksTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/nestedblocks");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks");

		Assert.assertEquals(5, a.getMaxNestedBlocks());

		Assert.assertEquals(5, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assert.assertEquals(2, a.getMethod("m2/0").get().getMaxNestedBlocks());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getMaxNestedBlocks());
	}

	@Test
	public void blocksWithNoClearOpenBracket() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks2");

		Assert.assertEquals(5, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assert.assertEquals(2, a.getMethod("m2/0").get().getMaxNestedBlocks());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getMaxNestedBlocks());

		Assert.assertEquals(5, a.getMaxNestedBlocks());
	}

	@Test
	public void loops() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks3");

		Assert.assertEquals(5, a.getMethod("m1/0").get().getMaxNestedBlocks());

		Assert.assertEquals(5, a.getMaxNestedBlocks());
	}

	@Test
	public void switchCases() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks4");

		Assert.assertEquals(4, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assert.assertEquals(4, a.getMethod("m2/0").get().getMaxNestedBlocks());

		Assert.assertEquals(4, a.getMaxNestedBlocks());
	}

	@Test
	public void blocksJustBecause() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks5");

		Assert.assertEquals(3, a.getMethod("m1/0").get().getMaxNestedBlocks());

		Assert.assertEquals(3, a.getMaxNestedBlocks());
	}

	@Test
	public void tryCatch() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks6");

		Assert.assertEquals(3, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assert.assertEquals(6, a.getMethod("m2/0").get().getMaxNestedBlocks());


		Assert.assertEquals(6, a.getMaxNestedBlocks());
	}

	@Test
	public void useSynchronized() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks7");

		Assert.assertEquals(2, a.getMethod("m1/0").get().getMaxNestedBlocks());
		Assert.assertEquals(2, a.getMethod("m1/0").get().getMaxNestedBlocks());


		Assert.assertEquals(2, a.getMaxNestedBlocks());
	}

	// Basic enums do not have nested blocks
	@Test
	public void enums() {
		CKClassResult a = report.get("nestedblocks.SimpleEnum");

		Assert.assertEquals(0, a.getMaxNestedBlocks());
	}

	// based on issue #40
	@Test
	public void classesWithNoMethods() {
		CKClassResult a = report.get("nestedblocks.NestedBlocks8");

		Assert.assertEquals(0, a.getMaxNestedBlocks());
	}
}
