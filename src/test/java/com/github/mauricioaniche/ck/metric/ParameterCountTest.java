package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class ParameterCountTest extends BaseTest{

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/parametercount");
	}

	@Test
	public void justDeclaration() {
		CKClassResult a = report.get("pcount.A");
		Map<String, Integer> variablesUsageA = a.getMethod("a/0").get().getVariablesUsage();
		Assert.assertEquals(Integer.valueOf(0), variablesUsageA.get("a"));
		Assert.assertEquals(Integer.valueOf(0), variablesUsageA.get("b"));
	}

	@Test
	public void normalUse() {
		CKClassResult a = report.get("pcount.A");
		Map<String, Integer> variablesUsageA = a.getMethod("b/0").get().getVariablesUsage();
		Assert.assertEquals(Integer.valueOf(4), variablesUsageA.get("a")); // a appears 5 times
		Assert.assertEquals(Integer.valueOf(2), variablesUsageA.get("b"));
	}

	@Test
	public void passingVariableToMethod() {
		CKClassResult a = report.get("pcount.A");
		Map<String, Integer> variablesUsageA = a.getMethod("bb/0").get().getVariablesUsage();
		Assert.assertEquals(Integer.valueOf(1), variablesUsageA.get("a"));
	}

	@Test
	public void methodParameters() {
		CKClassResult a = report.get("pcount.A");
		Map<String, Integer> variablesUsageA = a.getMethod("c/1[int]").get().getVariablesUsage();
		Assert.assertEquals(Integer.valueOf(0), variablesUsageA.get("a"));

		Map<String, Integer> variablesUsageB = a.getMethod("d/2[int,int]").get().getVariablesUsage();
		Assert.assertEquals(Integer.valueOf(4), variablesUsageB.get("a"));
		Assert.assertEquals(Integer.valueOf(0), variablesUsageB.get("b"));

		Map<String, Integer> variablesUsageC = a.getMethod("e/2[pcount.B,int]").get().getVariablesUsage();
		Assert.assertEquals(Integer.valueOf(2), variablesUsageC.get("a"));
		Assert.assertEquals(Integer.valueOf(0), variablesUsageC.get("b"));

	}

}
