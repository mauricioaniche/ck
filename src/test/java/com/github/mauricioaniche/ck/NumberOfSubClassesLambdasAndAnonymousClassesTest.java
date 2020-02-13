package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfSubClassesLambdasAndAnonymousClassesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = runDebug(fixturesDir() + "/subclasses");
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

	@Test
	public void subclassesInsideAnonymousClasses() {

		System.out.println(report.keySet());
		CKClassResult a = report.get("subclasses.SC2");
		// the subclass is inside the anonymous method inside the method (two levels below...), and not the class...
		Assert.assertEquals(0, a.getSubClassesQty());
		Assert.assertEquals(1, a.getAnonymousClassesQty());
		Assert.assertEquals(0, a.getLambdasQty());

		Assert.assertEquals(1, a.getMethod("m1/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getSubClassesQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getLambdasQty());

		CKClassResult b = report.get("subclasses.SC2$Anonymous1");
		Assert.assertEquals(1, b.getMethod("toString/0").get().getSubClassesQty());
		Assert.assertEquals(1, b.getSubClassesQty());
		Assert.assertEquals(0, b.getLambdasQty());
		Assert.assertEquals(0, b.getAnonymousClassesQty());

		CKClassResult sc = report.get("subclasses.SC2$1$1X");
		Assert.assertNotNull(sc);
		Assert.assertEquals(0, sc.getSubClassesQty());
		Assert.assertEquals(0, sc.getLambdasQty());
		Assert.assertEquals(0, sc.getAnonymousClassesQty());
	}
}
