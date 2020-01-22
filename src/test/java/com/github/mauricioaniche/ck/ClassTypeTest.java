package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class ClassTypeTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/class-types");
	}


	@Test
	public void identifyTypesCorrectly() {
		CKClassResult a = report.get("classtypes.A");
		Assert.assertEquals("class", a.getType());

		CKClassResult b = report.get("classtypes.A$B");
		Assert.assertEquals("subclass", b.getType());

		CKClassResult mathOperation = report.get("classtypes.MathOperation");
		Assert.assertEquals("interface", mathOperation.getType());

	}


}
