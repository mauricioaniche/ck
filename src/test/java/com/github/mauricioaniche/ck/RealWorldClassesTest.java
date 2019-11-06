package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

public class RealWorldClassesTest extends BaseTest {

	@Test
	public void xmlInputFactory() {
		Map<String, CKClassResult> report = run(fixturesDir() + "/real-world/");

		CKClassResult a = report.get("javax.xml.stream.XMLInputFactory");
		Assert.assertEquals(29, a.getNumberOfMethods());

		Optional<CKMethodResult> m1 = a.getMethod("getXMLReporter/0");
		Assert.assertTrue(org.eclipse.jdt.core.dom.Modifier.isAbstract(m1.get().getModifiers()));
	}
}
