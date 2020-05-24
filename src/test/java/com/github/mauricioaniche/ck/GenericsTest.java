package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenericsTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/generics");
	}

	@Test
	public void genericMethods() {
		CKClassResult r = report.get("bg.Generics");
		Assertions.assertEquals(2, r.getMethods().size());

		// method names are the same, but starting line is different
		Assertions.assertEquals(1, r.getMethods().stream()
				.filter(x -> x.getMethodName().equals("notEmpty/3[T,java.lang.String,java.lang.Object[]]") && x.getStartLine() == 9).count());

		Assertions.assertEquals(1, r.getMethods().stream()
				.filter(x -> x.getMethodName().equals("notEmpty/3[T,java.lang.String,java.lang.Object[]]") && x.getStartLine() == 13).count());

	}

	@Test
	public void noGenericsInClassName() {
		CKClassResult r = report.get("bg.Generics2");

		Assertions.assertNotNull(r);

	}
}
