package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class CBOTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/rfc");
	}
	
	@Test
	public void ignoreJavaTypes() {
		CKNumber a = report.getByClassName("cbo.Coupling0");
		Assert.assertEquals(0, a.getCbo());
	}
	
	@Test
	public void countDifferentPossibilitiesOfDependencies() {
		
		CKNumber a = report.getByClassName("cbo.Coupling1");
		Assert.assertEquals(7, a.getCbo());
	}
	
	@Test
	public void countEvenWhenNotResolved() {
		
		CKNumber a = report.getByClassName("cbo.Coupling3");
		Assert.assertEquals(1, a.getCbo());
	}
	
	@Test
	public void countInterfacesAndInheritances() {
		
		CKNumber b = report.getByClassName("cbo.Coupling2");
		Assert.assertEquals(5, b.getCbo());
	}
}
