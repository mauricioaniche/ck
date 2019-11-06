package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class GenericsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/generics");
	}

	@Test
	public void genericMethods() {
		CKClassResult r = report.get("bg.Generics");
		Assert.assertEquals(2, r.getMethods().size());

		// method names are the same, but starting line is different
		Assert.assertEquals(1, r.getMethods().stream()
				.filter(x -> x.getMethodName().equals("notEmpty/3[T,java.lang.String,java.lang.Object[]]") && x.getStartLine() == 9).count());

		Assert.assertEquals(1, r.getMethods().stream()
				.filter(x -> x.getMethodName().equals("notEmpty/3[T,java.lang.String,java.lang.Object[]]") && x.getStartLine() == 13).count());

	}
}
