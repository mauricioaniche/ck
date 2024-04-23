package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfInnerClassesLambdasAndAnonymousClassesTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/innerclasses");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("innerclasses.MessyClass");

		Assertions.assertEquals(2, a.getAnonymousClassesQty());
		Assertions.assertEquals(1, a.getLambdasQty());
		Assertions.assertEquals(3, a.getInnerClassesQty());

		Assertions.assertEquals(0, a.getMethod("m1/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, a.getMethod("m1/0").get().getLambdasQty());
		Assertions.assertEquals(0, a.getMethod("m1/0").get().getInnerClassesQty());

		Assertions.assertEquals(0, a.getMethod("m2/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, a.getMethod("m2/0").get().getLambdasQty());
		Assertions.assertEquals(0, a.getMethod("m2/0").get().getInnerClassesQty());

		Assertions.assertEquals(1, a.getMethod("m3/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getLambdasQty());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getInnerClassesQty());

		Assertions.assertEquals(1, a.getMethod("m4/0").get().getLambdasQty());
		Assertions.assertEquals(0, a.getMethod("m4/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, a.getMethod("m4/0").get().getInnerClassesQty());
		
		Assertions.assertEquals(1, a.getMethod("m5/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, a.getMethod("m5/0").get().getLambdasQty());
		Assertions.assertEquals(0, a.getMethod("m5/0").get().getInnerClassesQty());

		Assertions.assertEquals(1, a.getMethod("m6/0").get().getInnerClassesQty());
		Assertions.assertEquals(0, a.getMethod("m6/0").get().getLambdasQty());
		Assertions.assertEquals(0, a.getMethod("m6/0").get().getAnonymousClassesQty());
		
		CKClassResult b = report.get("innerclasses.MessyClass");

		Assertions.assertEquals(2, b.getAnonymousClassesQty());
		Assertions.assertEquals(1, b.getLambdasQty());
		Assertions.assertEquals(3, b.getInnerClassesQty());

		Assertions.assertEquals(0, b.getMethod("m1/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, b.getMethod("m1/0").get().getLambdasQty());
		Assertions.assertEquals(0, b.getMethod("m1/0").get().getInnerClassesQty());

		Assertions.assertEquals(0, b.getMethod("m2/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, b.getMethod("m2/0").get().getLambdasQty());
		Assertions.assertEquals(0, b.getMethod("m2/0").get().getInnerClassesQty());

		Assertions.assertEquals(1, b.getMethod("m3/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, b.getMethod("m3/0").get().getLambdasQty());
		Assertions.assertEquals(0, b.getMethod("m3/0").get().getInnerClassesQty());

		Assertions.assertEquals(1, b.getMethod("m4/0").get().getLambdasQty());
		Assertions.assertEquals(0, b.getMethod("m4/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, b.getMethod("m4/0").get().getInnerClassesQty());


		Assertions.assertEquals(1, b.getMethod("m5/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, b.getMethod("m5/0").get().getLambdasQty());
		Assertions.assertEquals(0, b.getMethod("m5/0").get().getInnerClassesQty());

		Assertions.assertEquals(1, b.getMethod("m6/0").get().getInnerClassesQty());
		Assertions.assertEquals(0, b.getMethod("m6/0").get().getLambdasQty());
		Assertions.assertEquals(0, b.getMethod("m6/0").get().getAnonymousClassesQty());
	}

	@Test
	public void innerclassesInsideAnonymousClasses() {

		CKClassResult a = report.get("innerclasses.SC2");
		// the innerclass is inside the anonymous method inside the method (two levels below...), and not the class...
		Assertions.assertEquals(0, a.getInnerClassesQty());
		Assertions.assertEquals(1, a.getAnonymousClassesQty());
		Assertions.assertEquals(0, a.getLambdasQty());

		Assertions.assertEquals(1, a.getMethod("m1/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, a.getMethod("m1/0").get().getInnerClassesQty());
		Assertions.assertEquals(0, a.getMethod("m1/0").get().getLambdasQty());

		CKClassResult b = report.get("innerclasses.SC2$Anonymous1");
		Assertions.assertEquals(1, b.getMethod("toString/0").get().getInnerClassesQty());
		Assertions.assertEquals(1, b.getInnerClassesQty());
		Assertions.assertEquals(0, b.getLambdasQty());
		Assertions.assertEquals(0, b.getAnonymousClassesQty());

		CKClassResult sc = report.get("innerclasses.SC2$1$1X");
		Assertions.assertNotNull(sc);
		Assertions.assertEquals(0, sc.getInnerClassesQty());
		Assertions.assertEquals(0, sc.getLambdasQty());
		Assertions.assertEquals(0, sc.getAnonymousClassesQty());
		
		CKClassResult x = report.get("innerclasses.RecordSC2");
		// the innerclass is inside the anonymous method inside the method (two levels below...), and not the class...
		Assertions.assertEquals(0, x.getInnerClassesQty());
		Assertions.assertEquals(1, x.getAnonymousClassesQty());
		Assertions.assertEquals(0, x.getLambdasQty());

		Assertions.assertEquals(1, x.getMethod("m1/0").get().getAnonymousClassesQty());
		Assertions.assertEquals(0, x.getMethod("m1/0").get().getInnerClassesQty());
		Assertions.assertEquals(0, x.getMethod("m1/0").get().getLambdasQty());

		CKClassResult y = report.get("innerclasses.SC2$Anonymous1");
		Assertions.assertEquals(1, y.getMethod("toString/0").get().getInnerClassesQty());
		Assertions.assertEquals(1, y.getInnerClassesQty());
		Assertions.assertEquals(0, y.getLambdasQty());
		Assertions.assertEquals(0, y.getAnonymousClassesQty());

		CKClassResult z = report.get("innerclasses.SC2$1$1X");
		Assertions.assertNotNull(z);
		Assertions.assertEquals(0, z.getInnerClassesQty());
		Assertions.assertEquals(0, z.getLambdasQty());
		Assertions.assertEquals(0, z.getAnonymousClassesQty());
	}
}
