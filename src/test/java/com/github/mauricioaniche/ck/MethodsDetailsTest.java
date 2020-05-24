package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MethodsDetailsTest extends BaseTest{

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/details-methods");
	}

	@Test
	public void loc() {
		CKClassResult a = report.get("methods2.A1");
		Assertions.assertEquals(6, a.getMethod("a/0").get().getLoc());
		Assertions.assertEquals(2, a.getMethod("b/0").get().getLoc());
	}

	@Test
	public void parameterQty() {
		CKClassResult a = report.get("methods2.A1");
		Assertions.assertEquals(3, a.getMethod("c/3[int,int,methods2.A]").get().getParametersQty());
		Assertions.assertEquals(0, a.getMethod("a/0").get().getParametersQty());
	}

	@Test
	public void variableQty() {
		CKClassResult a = report.get("methods2.A1");
		Assertions.assertEquals(5, a.getMethod("a/0").get().getVariablesQty());
		Assertions.assertEquals(1, a.getMethod("c/3[int,int,methods2.A]").get().getVariablesQty());
		Assertions.assertEquals(0, a.getMethod("b/0").get().getVariablesQty());
	}

	@Test
	public void returnQty() {
		CKClassResult a = report.get("methods2.A1");
		Assertions.assertEquals(0, a.getMethod("c/3[int,int,methods2.A]").get().getReturnQty());
		Assertions.assertEquals(0, a.getMethod("a/0").get().getReturnQty());
		Assertions.assertEquals(3, a.getMethod("d/0").get().getReturnQty());
		Assertions.assertEquals(1, a.getMethod("e/0").get().getReturnQty());
	}

	@Test
	public void lineNumber() {
		CKClassResult a = report.get("methods2.A1");
		Assertions.assertEquals(18, a.getMethod("c/3[int,int,methods2.A]").get().getStartLine());
		Assertions.assertEquals(5, a.getMethod("a/0").get().getStartLine());
		Assertions.assertEquals(22, a.getMethod("d/0").get().getStartLine());
		Assertions.assertEquals(29, a.getMethod("e/0").get().getStartLine());
	}
}
