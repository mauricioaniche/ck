package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
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



}
