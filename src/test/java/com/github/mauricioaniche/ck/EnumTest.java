package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnumTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/enumdecl");
	}

	@Test
	public void testEnum() {
		CKClassResult a = report.get("enumd.EnumDecl");

		Assertions.assertEquals(2, a.getMethods().size());

		CKMethodResult m1 = a.getMethods().stream().filter(x -> x.getMethodName().contains("getX")).findFirst().get();
		Assertions.assertEquals("getX/0", m1.getMethodName());
		Assertions.assertEquals(1, m1.getReturnQty());
		Assertions.assertEquals(1, m1.getLoopQty());

	}

	@Test
	public void maxNestedDepth() {
		CKClassResult b = report.get("enumd.EnumDecl2");
		Assertions.assertEquals(1, b.getMethod("url/0").get().getMaxNestedBlocks());
		Assertions.assertEquals(4, b.getMethod("m2/0").get().getMaxNestedBlocks());
	}

	@Test
	public void subclasses() {
		CKClassResult b = report.get("enumd.EnumDecl3");

		Assertions.assertEquals(1, b.getMethod("getX/0").get().getInnerClassesQty());
		Assertions.assertEquals(2, b.getInnerClassesQty());

		CKClassResult sc = report.get("enumd.EnumDecl3$1Other");
		Assertions.assertEquals(4, sc.getNumberOfMethods());
		Assertions.assertEquals(3, sc.getMethod("x1/0").get().getWmc());
		Assertions.assertEquals(2, sc.getMethod("x1/0").get().getVariablesQty());

	}



}
