package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class LCOMTest extends BaseTest {

	private static Map<String, CKNumber> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/lcom");
	}
	
	@Test
	public void should_count_lcom() {
		
		CKNumber a = report.get("lcom.TripStatusBean");
		Assert.assertEquals(1415, a.getLcom());

		CKNumber b = report.get("lcom.SimpleGetterAndSetter");
		Assert.assertEquals(0, b.getLcom());

		CKNumber c = report.get("lcom.SimpleGetterAndSetter2");
		Assert.assertEquals(2, c.getLcom());

		CKNumber d = report.get("lcom.TermsOfServiceController");
		Assert.assertEquals(0, d.getLcom());

	}
	
}
