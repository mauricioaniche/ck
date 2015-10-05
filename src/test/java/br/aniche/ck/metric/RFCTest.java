package br.aniche.ck.metric;

import org.junit.Test;

import br.aniche.ck.CK;
import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;
import org.junit.Assert;

public class RFCTest {

	@Test
	public void should_detect_wmc() {
		
		CKReport report = new CK().calculate("/Users/mauricioaniche/workspace/ck/src/test/p1/rfc");

		CKNumber a = report.getByClassName("rfc.GO");
		Assert.assertEquals(3, a.getRfc());
	}
}
