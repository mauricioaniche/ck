package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class BadGenericsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/badgenerics");
	}

	// we can't really differentiate methods with same name and types that
	// only differ because of the generic type
	@Test
	public void exception() {
		CKClassResult r = report.get("bg.BG");
		Assert.assertTrue(r.isError());
		Assert.assertTrue(r.getErrorMessage().contains("already visited"));
	}
}
