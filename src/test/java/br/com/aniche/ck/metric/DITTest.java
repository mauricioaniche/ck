package br.com.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.com.aniche.ck.CK;
import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public class DITTest extends BaseTest {

	@Test
	public void should_detect_simple_inheritance() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("dit.A");
		Assert.assertEquals(1, a.getDit());
		CKNumber b = report.getByClassName("dit.B");
		Assert.assertEquals(2, b.getDit());
		CKNumber c = report.getByClassName("dit.C");
		Assert.assertEquals(3, c.getDit());
	}
}
