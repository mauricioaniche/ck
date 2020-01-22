package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class ClassTypeTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/class-types");
	}


	@Test
	public void identifyTypesCorrectly() {

		Assert.assertEquals(5, report.size());

		CKClassResult a = report.get("classtypes.A");
		Assert.assertEquals("class", a.getType());

		CKClassResult b = report.get("classtypes.A$B");
		Assert.assertEquals("subclass", b.getType());

		CKClassResult mathOperation = report.get("classtypes.MathOperation");
		Assert.assertEquals("interface", mathOperation.getType());

		CKClassResult anon1 = report.get("classtypes.A$Anonymous1");
		Assert.assertEquals("anonymous", anon1.getType());

		CKClassResult anon2 = report.get("classtypes.A$Anonymous2");
		Assert.assertEquals("anonymous", anon2.getType());


	}


}
