package br.com.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.com.aniche.ck.CK;
import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public class NOCTest extends BaseTest {

	@Test
	public void should_detect_children() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("dit.A");
		Assert.assertEquals(1, a.getNoc());
		CKNumber b = report.getByClassName("dit.B");
		Assert.assertEquals(2, b.getNoc());
		CKNumber c = report.getByClassName("dit.C");
		Assert.assertEquals(0, c.getNoc());
	}
}
