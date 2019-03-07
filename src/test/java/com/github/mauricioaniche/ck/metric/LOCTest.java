package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class LOCTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/cbo");
	}
	
	@Test
	public void countLinesIgnoringEmptyLines() {
		CKClassResult a = report.get("cbo.Coupling1");
		Assert.assertEquals(11, a.getLoc());
	}
	
}
