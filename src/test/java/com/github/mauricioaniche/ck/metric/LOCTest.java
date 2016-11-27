package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class LOCTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/cbo");
	}
	
	@Test
	public void countLinesIgnoringEmptyLines() {
		CKNumber a = report.getByClassName("cbo.Coupling1");
		Assert.assertEquals(11, a.getLoc());
	}
	
}
