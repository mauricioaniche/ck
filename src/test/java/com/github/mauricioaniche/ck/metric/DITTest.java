package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class DITTest extends BaseTest {


	private static Map<String, CKNumber> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/dit");
	}
	
	@Test
	public void everyOneHasObjectAsFather() {
		CKNumber a = report.get("dit.A");
		Assert.assertEquals(1, a.getDit());
	}

	@Test
	public void firstLevelInheritance() {
		CKNumber b = report.get("dit.B");
		Assert.assertEquals(2, b.getDit());
	}
	
	@Test
	public void twoLevelsInheritance() {
		CKNumber c = report.get("dit.C");
		Assert.assertEquals(3, c.getDit());

		CKNumber c2 = report.get("dit.C2");
		Assert.assertEquals(3, c2.getDit());
	}
	
	@Test
	public void threeLevelsInheritance() {
		CKNumber d = report.get("dit.D");
		Assert.assertEquals(4, d.getDit());
	}
	
	@Test
	public void countEvenClassesNotResolved() {
		CKNumber a = report.get("dit.X");
		Assert.assertEquals(2, a.getDit());
	}

}
