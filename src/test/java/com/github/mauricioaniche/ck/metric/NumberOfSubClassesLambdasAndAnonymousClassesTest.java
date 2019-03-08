package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfSubClassesLambdasAndAnonymousClassesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/subclasses");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("subclasses.MessyClass");

		Assert.assertEquals(2, a.getAnonymousClassesQty());
		Assert.assertEquals(1, a.getLambdasQty());
		Assert.assertEquals(3, a.getSubClassesQty());

		Assert.assertEquals(0, a.getMethod("m1/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getSubClassesQty());

		Assert.assertEquals(0, a.getMethod("m2/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m2/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m2/0").get().getSubClassesQty());

		Assert.assertEquals(1, a.getMethod("m3/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getSubClassesQty());

		Assert.assertEquals(1, a.getMethod("m4/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m4/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m4/0").get().getSubClassesQty());


		Assert.assertEquals(1, a.getMethod("m5/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m5/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m5/0").get().getSubClassesQty());

		Assert.assertEquals(1, a.getMethod("m6/0").get().getSubClassesQty());
		Assert.assertEquals(0, a.getMethod("m6/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m6/0").get().getAnonymousClassesQty());
	}
}
