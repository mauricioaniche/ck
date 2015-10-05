package br.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.aniche.ck.CK;
import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;

public class DITTest {

	@Test
	public void should_detect_simple_inheritance() {
		
		CKReport report = new CK().calculate("/Users/mauricioaniche/workspace/ck/src/test/p1");

		CKNumber a = report.getByClassName("A.java");
		Assert.assertEquals(1, a.getDit());
		CKNumber b = report.getByClassName("B.java");
		Assert.assertEquals(2, b.getDit());
		CKNumber c = report.getByClassName("C.java");
		Assert.assertEquals(3, c.getDit());
	}
}
