package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class DITTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/dit");
	}
	
	@Test
	public void everyOneHasObjectAsFather() {
		CKNumber a = report.getByClassName("dit.A");
		Assert.assertEquals(1, a.getDit());
	}

	@Test
	public void firstLevelInheritance() {
		CKNumber b = report.getByClassName("dit.B");
		Assert.assertEquals(2, b.getDit());
	}
	
	@Test
	public void twoLevelsInheritance() {
		CKNumber c = report.getByClassName("dit.C");
		Assert.assertEquals(3, c.getDit());

		CKNumber c2 = report.getByClassName("dit.C2");
		Assert.assertEquals(3, c2.getDit());
	}
	
	@Test
	public void threeLevelsInheritance() {
		CKNumber d = report.getByClassName("dit.D");
		Assert.assertEquals(4, d.getDit());
	}
	
	@Test
	public void countEvenClassesNotResolved() {
		CKNumber a = report.getByClassName("dit.X");
		Assert.assertEquals(2, a.getDit());
	}

}
