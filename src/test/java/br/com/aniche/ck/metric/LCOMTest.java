package br.com.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.com.aniche.ck.CK;
import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public class LCOMTest extends BaseTest {

	@Test
	public void should_count_lcom() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("lcom.TripStatusBean");
		Assert.assertEquals(1485, a.getLcom());
	}
}
