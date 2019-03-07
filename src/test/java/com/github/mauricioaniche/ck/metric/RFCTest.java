package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class RFCTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/rfc");
	}

	@Test
	public void countMethodInvocations() {
		CKClassResult a = report.get("rfc.GO");
		Assert.assertEquals(3, a.getRfc());
	}

	@Test
	public void countSuperInvocations() {
		CKClassResult a = report.get("rfc.GO3");
		Assert.assertEquals(2, a.getRfc());
	}

	@Test
	public void notPossibleToDifferentiateTypesWithStaticAnalysis() {
		CKClassResult a = report.get("rfc.RFC3");
		Assert.assertEquals(1, a.getRfc());
	}

	@Test
	public void doesNotCountConstructorInvocations() {
		CKClassResult a = report.get("rfc.RFC2");
		Assert.assertEquals(0, a.getRfc());
	}

	@Test
	public void methodLevel() {
		CKClassResult a = report.get("rfc.GO");
		Assert.assertEquals(2, a.getMethod("m1/0").get().getRfc());
		Assert.assertEquals(0, a.getMethod("m2/1[int]").get().getRfc());
		Assert.assertEquals(1, a.getMethod("m3/0").get().getRfc());

	}
}
