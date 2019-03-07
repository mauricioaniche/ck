package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class DITTest extends BaseTest {


	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/dit");
	}
	
	@Test
	public void everyOneHasObjectAsFather() {
		CKClassResult a = report.get("dit.A");
		Assert.assertEquals(1, a.getDit());
	}

	@Test
	public void firstLevelInheritance() {
		CKClassResult b = report.get("dit.B");
		Assert.assertEquals(2, b.getDit());
	}
	
	@Test
	public void twoLevelsInheritance() {
		CKClassResult c = report.get("dit.C");
		Assert.assertEquals(3, c.getDit());

		CKClassResult c2 = report.get("dit.C2");
		Assert.assertEquals(3, c2.getDit());
	}
	
	@Test
	public void threeLevelsInheritance() {
		CKClassResult d = report.get("dit.D");
		Assert.assertEquals(4, d.getDit());
	}
	
	@Test
	public void countEvenClassesNotResolved() {
		CKClassResult a = report.get("dit.X");
		Assert.assertEquals(2, a.getDit());
	}

}
