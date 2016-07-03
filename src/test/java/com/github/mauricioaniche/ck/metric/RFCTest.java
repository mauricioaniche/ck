package com.github.mauricioaniche.ck.metric;

import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

import org.junit.Assert;

public class RFCTest extends BaseTest {

	@Test
	public void should_detect_rfc() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("rfc.GO");
		Assert.assertEquals(3, a.getRfc());
	}
}
