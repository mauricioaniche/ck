package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LCOMTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/lcom");
	}
	
	@Test
	public void should_count_lcom() {
		
		CKClassResult a = report.get("lcom.TripStatusBean");
		Assertions.assertEquals(1415, a.getLcom());

		CKClassResult b = report.get("lcom.SimpleGetterAndSetter");
		Assertions.assertEquals(0, b.getLcom());

		CKClassResult c = report.get("lcom.SimpleGetterAndSetter2");
		Assertions.assertEquals(2, c.getLcom());

		CKClassResult d = report.get("lcom.TermsOfServiceController");
		Assertions.assertEquals(0, d.getLcom());

	}
	
}
