package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class LOCTest extends BaseTest {

	private static Map<String, CKNumber> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/cbo");
	}
	
	@Test
	public void countLinesIgnoringEmptyLines() {
		CKNumber a = report.get("cbo.Coupling1");
		Assert.assertEquals(11, a.getLoc());
	}
	
}
