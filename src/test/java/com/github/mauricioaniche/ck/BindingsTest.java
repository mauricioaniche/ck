package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class BindingsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/bindings");
	}


	@Test
	public void fullNameEvenWhenTypesAreNotAvailable() {
		CKClassResult a = report.get("bind.BindingFail1");
		Assert.assertNotNull(a);
	}
	
}
