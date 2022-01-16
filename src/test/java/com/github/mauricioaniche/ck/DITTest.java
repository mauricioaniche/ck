package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.NOCExtras;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DITTest extends BaseTest {

	private Map<String, CKClassResult> report;

	@BeforeAll
	public void setUp() {
		NOCExtras.resetInstance();
		report = run(fixturesDir() + "/dit");
	}
	
	@Test
	public void everyOneHasObjectAsFather() {
		CKClassResult a = report.get("dit.A");
		Assertions.assertEquals(1, a.getDit());
	}

	@Test
	public void firstLevelInheritance() {
		CKClassResult b = report.get("dit.B");
		Assertions.assertEquals(2, b.getDit());
	}
	
	@Test
	public void twoLevelsInheritance() {
		CKClassResult c = report.get("dit.C");
		Assertions.assertEquals(3, c.getDit());

		CKClassResult c2 = report.get("dit.C2");
		Assertions.assertEquals(3, c2.getDit());
	}
	
	@Test
	public void threeLevelsInheritance() {
		CKClassResult d = report.get("dit.D");
		Assertions.assertEquals(4, d.getDit());
	}
	
	@Test
	public void countEvenClassesNotResolved() {
		CKClassResult a = report.get("dit.X");
		Assertions.assertEquals(2, a.getDit());
	}

}
