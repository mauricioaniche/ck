package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class MetricsPerClassesAndInnerClassesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/innerclasses");
	}

	@Test
	public void metricsPerClass() {

		Assert.assertEquals(9, report.values().size());

		CKClassResult a = report.get("innerclasses.MessyClass");
		Assert.assertEquals(6, a.getNumberOfMethods());
		Assert.assertEquals("class", a.getType());

		CKClassResult sc1 = report.get("innerclasses.MessyClass$InnerClass1");
		Assert.assertEquals(2, sc1.getNumberOfMethods());
		Assert.assertEquals(2, sc1.getMethods().stream().filter(x -> x.getMethodName().equals("m1/0")).findFirst().get().getMaxNestedBlocks());
		Assert.assertEquals("innerclass", sc1.getType());

		CKClassResult sc2 = report.get("innerclasses.MessyClass$InnerClass2");
		Assert.assertEquals(3, sc2.getNumberOfMethods());
		Assert.assertEquals("innerclass", sc2.getType());

		CKClassResult sc3 = report.get("innerclasses.MessyClass$1InnerClass3");
		Assert.assertEquals(1, sc3.getNumberOfMethods());
		Assert.assertEquals("innerclass", sc3.getType());

		CKClassResult an1 = report.get("innerclasses.MessyClass$Anonymous1");
		Assert.assertEquals(1, an1.getNumberOfMethods());
		Assert.assertEquals("anonymous", an1.getType());

		CKClassResult an2 = report.get("innerclasses.MessyClass$Anonymous2");
		Assert.assertEquals(2, an2.getNumberOfMethods());
		Assert.assertEquals("anonymous", an2.getType());


		CKClassResult ysc2 = report.get("innerclasses.SC2");
		Assert.assertEquals("class", ysc2.getType());
		CKClassResult ysc2a = report.get("innerclasses.SC2$Anonymous1");
		Assert.assertEquals("anonymous", ysc2a.getType());
		CKClassResult ysc2x = report.get("innerclasses.SC2$1$1X");
		Assert.assertEquals("innerclass", ysc2x.getType());

	}




}
