package br.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.aniche.ck.CK;
import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;

public class NOCTest {

	@Test
	public void should_detect_simple_inheritance() {
		
		CKReport report = new CK().calculate("/Users/mauricioaniche/workspace/ck/src/test/p1");

		CKNumber a = report.getByClassName("dit.A");
		Assert.assertEquals(1, a.getNoc());
		CKNumber b = report.getByClassName("dit.B");
		Assert.assertEquals(2, b.getNoc());
		CKNumber c = report.getByClassName("dit.C");
		Assert.assertEquals(0, c.getNoc());
	}
}
