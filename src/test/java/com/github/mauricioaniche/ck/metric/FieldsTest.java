package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class FieldsTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/fields");
	}
	
	@Test
	public void all() {
		CKNumber a = report.getByClassName("fields.Fields");
		Assert.assertEquals(5, a.getNof());
	}

	@Test
	public void allPublic() {
		CKNumber a = report.getByClassName("fields.Fields");
		Assert.assertEquals(2, a.getNopf());
	}

	@Test
	public void allStatic() {
		CKNumber a = report.getByClassName("fields.Fields");
		Assert.assertEquals(2, a.getNosf());
	}
}
