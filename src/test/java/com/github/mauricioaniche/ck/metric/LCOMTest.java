package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class LCOMTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/lcom");
	}
	
	@Test
	public void should_count_lcom() {
		
		CKClassResult a = report.get("lcom.TripStatusBean");
		Assert.assertEquals(1415, a.getLcom());

		CKClassResult b = report.get("lcom.SimpleGetterAndSetter");
		Assert.assertEquals(0, b.getLcom());

		CKClassResult c = report.get("lcom.SimpleGetterAndSetter2");
		Assert.assertEquals(2, c.getLcom());

		CKClassResult d = report.get("lcom.TermsOfServiceController");
		Assert.assertEquals(0, d.getLcom());

	}
	
}
