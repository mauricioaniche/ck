package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

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
