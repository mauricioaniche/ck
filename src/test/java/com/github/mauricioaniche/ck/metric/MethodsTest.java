package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class MethodsTest extends BaseTest {

	private static Map<String, CKNumber> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/methods");
	}
	
	@Test
	public void all() {
		CKNumber a = report.get("methods.Methods");
		Assert.assertEquals(5, a.getNom());
	}

	@Test
	public void allPublic() {
		CKNumber a = report.get("methods.Methods");
		Assert.assertEquals(2, a.getNopm());
	}

	@Test
	public void allStatic() {
		CKNumber a = report.get("methods.Methods");
		Assert.assertEquals(2, a.getNosm());
	}
}
