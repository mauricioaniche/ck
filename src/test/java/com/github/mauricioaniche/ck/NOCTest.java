package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.NOCExtras;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NOCTest extends BaseTest{

	@BeforeAll
	public void setUp() {
		NOCExtras.resetInstance();
		report = run(fixturesDir() + "/dit");
	}
	
	@Test
	public void shouldDetectChildren() {
		CKClassResult a = report.get("dit.A");
		Assertions.assertEquals(1, a.getNoc());
		
		CKClassResult b = report.get("dit.B");
		Assertions.assertEquals(2, b.getNoc());
		
		CKClassResult c = report.get("dit.C");
		Assertions.assertEquals(1, c.getNoc());
		
		CKClassResult d = report.get("dit.D");
		Assertions.assertEquals(0, d.getNoc());
		
		CKClassResult e = report.get("dit.C2");
		Assertions.assertEquals(0, e.getNoc());
		
		CKClassResult f = report.get("dit.X");
		Assertions.assertEquals(0, f.getNoc());
	}
	
}
