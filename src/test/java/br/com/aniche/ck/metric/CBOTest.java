package br.com.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.com.aniche.ck.CK;
import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public class CBOTest extends BaseTest {

	@Test
	public void should_count_cbo() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("cbo.Coupling1");
		Assert.assertEquals(7, a.getCbo());

		CKNumber b = report.getByClassName("cbo.Coupling2");
		Assert.assertEquals(4, b.getCbo());
	}
}
