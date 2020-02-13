package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class MetricsPerClassesAndSubClassesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/subclasses");
	}

	@Test
	public void metricsPerClass() {

		Assert.assertEquals(9, report.values().size());

		CKClassResult a = report.get("subclasses.MessyClass");
		Assert.assertEquals(6, a.getNumberOfMethods());
		Assert.assertEquals("class", a.getType());

		CKClassResult sc1 = report.get("subclasses.MessyClass$SubClass1");
		Assert.assertEquals(2, sc1.getNumberOfMethods());
		Assert.assertEquals(2, sc1.getMethods().stream().filter(x -> x.getMethodName().equals("m1/0")).findFirst().get().getMaxNestedBlocks());
		Assert.assertEquals("subclass", sc1.getType());

		CKClassResult sc2 = report.get("subclasses.MessyClass$SubClass2");
		Assert.assertEquals(3, sc2.getNumberOfMethods());
		Assert.assertEquals("subclass", sc2.getType());

		CKClassResult sc3 = report.get("subclasses.MessyClass$1SubClass3");
		Assert.assertEquals(1, sc3.getNumberOfMethods());
		Assert.assertEquals("subclass", sc3.getType());

		CKClassResult an1 = report.get("subclasses.MessyClass$Anonymous1");
		Assert.assertEquals(1, an1.getNumberOfMethods());
		Assert.assertEquals("anonymous", an1.getType());

		CKClassResult an2 = report.get("subclasses.MessyClass$Anonymous2");
		Assert.assertEquals(2, an2.getNumberOfMethods());
		Assert.assertEquals("anonymous", an2.getType());


		CKClassResult ysc2 = report.get("subclasses.SC2");
		Assert.assertEquals("class", ysc2.getType());
		CKClassResult ysc2a = report.get("subclasses.SC2$Anonymous1");
		Assert.assertEquals("anonymous", ysc2a.getType());
		CKClassResult ysc2x = report.get("subclasses.SC2$1$1X");
		Assert.assertEquals("subclass", ysc2x.getType());

	}




}
