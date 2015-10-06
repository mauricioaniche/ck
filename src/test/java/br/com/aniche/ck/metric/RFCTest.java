package br.com.aniche.ck.metric;

import org.junit.Test;

import br.com.aniche.ck.CK;
import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

import org.junit.Assert;

public class RFCTest extends BaseTest {

	@Test
	public void should_detect_wmc() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("rfc.GO");
		Assert.assertEquals(3, a.getRfc());
	}
}
