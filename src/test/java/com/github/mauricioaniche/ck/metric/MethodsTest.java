package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class MethodsTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/methods");
	}
	
	@Test
	public void all() {
		CKNumber a = report.getByClassName("methods.Methods");
		Assert.assertEquals(5, a.getNom());
	}

	@Test
	public void allPublic() {
		CKNumber a = report.getByClassName("methods.Methods");
		Assert.assertEquals(2, a.getNopm());
	}

	@Test
	public void allStatic() {
		CKNumber a = report.getByClassName("methods.Methods");
		Assert.assertEquals(2, a.getNosm());
	}
}
