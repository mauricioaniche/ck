package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

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
