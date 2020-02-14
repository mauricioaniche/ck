package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class EnumTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/enumdecl");
	}

	@Test
	public void testEnum() {
		CKClassResult a = report.get("enumd.EnumDecl");

		Assert.assertEquals(2, a.getMethods().size());

		CKMethodResult m1 = a.getMethods().stream().filter(x -> x.getMethodName().contains("getX")).findFirst().get();
		Assert.assertEquals("getX/0", m1.getMethodName());
		Assert.assertEquals(1, m1.getReturnQty());
		Assert.assertEquals(1, m1.getLoopQty());

	}

	@Test
	public void maxNestedDepth() {
		CKClassResult b = report.get("enumd.EnumDecl2");
		Assert.assertEquals(1, b.getMethod("url/0").get().getMaxNestedBlocks());
		Assert.assertEquals(4, b.getMethod("m2/0").get().getMaxNestedBlocks());
	}

	@Test
	public void subclasses() {
		CKClassResult b = report.get("enumd.EnumDecl3");

		Assert.assertEquals(1, b.getMethod("getX/0").get().getInnerClassesQty());
		Assert.assertEquals(2, b.getInnerClassesQty());

		CKClassResult sc = report.get("enumd.EnumDecl3$1Other");
		Assert.assertEquals(4, sc.getNumberOfMethods());
		Assert.assertEquals(3, sc.getMethod("x1/0").get().getWmc());
		Assert.assertEquals(2, sc.getMethod("x1/0").get().getVariablesQty());

	}



}
