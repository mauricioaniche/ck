package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class NOCTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/dit");
	}
	
	@Test
	public void should_detect_children() {
		
		CKNumber a = report.getByClassName("dit.A");
		Assert.assertEquals(1, a.getNoc());
		CKNumber b = report.getByClassName("dit.B");
		Assert.assertEquals(2, b.getNoc());
		CKNumber c = report.getByClassName("dit.C");
		Assert.assertEquals(1, c.getNoc());
		CKNumber d = report.getByClassName("dit.D");
		Assert.assertEquals(0, d.getNoc());
	}
}
