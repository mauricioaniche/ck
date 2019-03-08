package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class MethodsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/methods");
	}
	
	@Test
	public void all() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(5, a.getNumberOfMethods());
	}

	@Test
	public void allPublic() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(2, a.getNumberOfPublicMethods());
	}

	@Test
	public void allStatic() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(2, a.getNumberOfStaticMethods());
	}
}
