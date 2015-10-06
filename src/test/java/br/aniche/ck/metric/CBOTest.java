package br.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.aniche.ck.CK;
import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;

public class CBOTest extends BaseTest {

	@Test
	public void should_count_cbo() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("cbo.Coupling1");
		Assert.assertEquals(8, a.getCbo());

		CKNumber b = report.getByClassName("cbo.Coupling2");
		Assert.assertEquals(6, b.getCbo());
	}
}
