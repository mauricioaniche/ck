package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MetricsPerClassesAndInnerClassesTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/innerclasses");
	}

	@Test
	public void metricsPerClass() {

		Assertions.assertEquals(9, report.values().size());

		CKClassResult a = report.get("innerclasses.MessyClass");
		Assertions.assertEquals(6, a.getNumberOfMethods());
		Assertions.assertEquals("class", a.getType());

		CKClassResult sc1 = report.get("innerclasses.MessyClass$InnerClass1");
		Assertions.assertEquals(2, sc1.getNumberOfMethods());
		Assertions.assertEquals(2, sc1.getMethods().stream().filter(x -> x.getMethodName().equals("m1/0")).findFirst().get().getMaxNestedBlocks());
		Assertions.assertEquals("innerclass", sc1.getType());

		CKClassResult sc2 = report.get("innerclasses.MessyClass$InnerClass2");
		Assertions.assertEquals(3, sc2.getNumberOfMethods());
		Assertions.assertEquals("innerclass", sc2.getType());

		CKClassResult sc3 = report.get("innerclasses.MessyClass$1InnerClass3");
		Assertions.assertEquals(1, sc3.getNumberOfMethods());
		Assertions.assertEquals("innerclass", sc3.getType());

		CKClassResult an1 = report.get("innerclasses.MessyClass$Anonymous1");
		Assertions.assertEquals(1, an1.getNumberOfMethods());
		Assertions.assertEquals("anonymous", an1.getType());

		CKClassResult an2 = report.get("innerclasses.MessyClass$Anonymous2");
		Assertions.assertEquals(2, an2.getNumberOfMethods());
		Assertions.assertEquals("anonymous", an2.getType());


		CKClassResult ysc2 = report.get("innerclasses.SC2");
		Assertions.assertEquals("class", ysc2.getType());
		CKClassResult ysc2a = report.get("innerclasses.SC2$Anonymous1");
		Assertions.assertEquals("anonymous", ysc2a.getType());
		CKClassResult ysc2x = report.get("innerclasses.SC2$1$1X");
		Assertions.assertEquals("innerclass", ysc2x.getType());

	}




}
