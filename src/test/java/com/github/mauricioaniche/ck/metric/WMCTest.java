package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class WMCTest extends BaseTest {

	@Test
	public void should_detect_wmc() {
		
		CKReport report = new CK().calculate(fixturesDir());

		CKNumber a = report.getByClassName("wmc.CC1");
		Assert.assertEquals(5, a.getWmc());
		CKNumber b = report.getByClassName("wmc.CC2");
		Assert.assertEquals(5, b.getWmc());
	}
}
