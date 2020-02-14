package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NumberOfInnerClassesLambdasAndAnonymousClassesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = runDebug(fixturesDir() + "/innerclasses");
		report = run(fixturesDir() + "/innerclasses");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("innerclasses.MessyClass");

		Assert.assertEquals(2, a.getAnonymousClassesQty());
		Assert.assertEquals(1, a.getLambdasQty());
		Assert.assertEquals(3, a.getInnerClassesQty());

		Assert.assertEquals(0, a.getMethod("m1/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getInnerClassesQty());

		Assert.assertEquals(0, a.getMethod("m2/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m2/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m2/0").get().getInnerClassesQty());

		Assert.assertEquals(1, a.getMethod("m3/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m3/0").get().getInnerClassesQty());

		Assert.assertEquals(1, a.getMethod("m4/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m4/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m4/0").get().getInnerClassesQty());


		Assert.assertEquals(1, a.getMethod("m5/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m5/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m5/0").get().getInnerClassesQty());

		Assert.assertEquals(1, a.getMethod("m6/0").get().getInnerClassesQty());
		Assert.assertEquals(0, a.getMethod("m6/0").get().getLambdasQty());
		Assert.assertEquals(0, a.getMethod("m6/0").get().getAnonymousClassesQty());
	}

	@Test
	public void innerclassesInsideAnonymousClasses() {

		System.out.println(report.keySet());
		CKClassResult a = report.get("innerclasses.SC2");
		// the innerclass is inside the anonymous method inside the method (two levels below...), and not the class...
		Assert.assertEquals(0, a.getInnerClassesQty());
		Assert.assertEquals(1, a.getAnonymousClassesQty());
		Assert.assertEquals(0, a.getLambdasQty());

		Assert.assertEquals(1, a.getMethod("m1/0").get().getAnonymousClassesQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getInnerClassesQty());
		Assert.assertEquals(0, a.getMethod("m1/0").get().getLambdasQty());

		CKClassResult b = report.get("innerclasses.SC2$Anonymous1");
		Assert.assertEquals(1, b.getMethod("toString/0").get().getInnerClassesQty());
		Assert.assertEquals(1, b.getInnerClassesQty());
		Assert.assertEquals(0, b.getLambdasQty());
		Assert.assertEquals(0, b.getAnonymousClassesQty());

		CKClassResult sc = report.get("innerclasses.SC2$1$1X");
		Assert.assertNotNull(sc);
		Assert.assertEquals(0, sc.getInnerClassesQty());
		Assert.assertEquals(0, sc.getLambdasQty());
		Assert.assertEquals(0, sc.getAnonymousClassesQty());
	}
}
