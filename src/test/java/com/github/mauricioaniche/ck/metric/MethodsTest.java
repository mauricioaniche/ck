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
		Assert.assertEquals(8, a.getNumberOfMethods());
	}

	@Test
	public void allPublic() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(3, a.getNumberOfPublicMethods());
	}

	@Test
	public void allStatic() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(2, a.getNumberOfStaticMethods());
	}

	@Test
	public void allDefault() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(1, a.getNumberOfDefaultMethods());
	}

	@Test
	public void allPrivate() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(3, a.getNumberOfPrivateMethods());
	}

	@Test
	public void allProtected() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(1, a.getNumberOfProtectedMethods());
	}


	@Test
	public void allSynchronized() {
		CKClassResult a = report.get("methods.Methods");
		Assert.assertEquals(1, a.getNumberOfSynchronizedMethods());
	}

	@Test
	public void allAbstract() {
		CKClassResult a = report.get("methods.Methods2");
		Assert.assertEquals(2, a.getNumberOfMethods());
		Assert.assertEquals(1, a.getNumberOfAbstractMethods());
	}
}
