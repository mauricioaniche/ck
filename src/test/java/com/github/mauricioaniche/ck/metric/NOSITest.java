package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class NOSITest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/nosi");
	}
	
	@Test
	public void staticInvocations() {
		CKClassResult a = report.get("nosi.Class2");
		Assert.assertEquals(1, a.getNosi());
	}

	@Test
	public void staticInvocationsToMethodsInTheSameClass() {
		CKClassResult a = report.get("nosi.Class3");
		Assert.assertEquals(2, a.getNosi());
	}

	@Test
	public void doesNotUnderstandWhenNotResolvable() {
		CKClassResult a = report.get("nosi.Class1");
		Assert.assertEquals(0, a.getNosi());
	}
}
