package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class CBOTest extends BaseTest {


	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/cbo");
	}
	
	@Test
	public void ignoreJavaTypes() {
		CKClassResult a = report.get("cbo.Coupling0");
		Assert.assertEquals(0, a.getCbo());
	}
	
	@Test
	public void countDifferentPossibilitiesOfDependencies() {
		
		CKClassResult a = report.get("cbo.Coupling1");
		Assert.assertEquals(6, a.getCbo());
	}
	
	@Test
	public void countEvenWhenNotResolved() {
		
		CKClassResult a = report.get("cbo.Coupling3");
		Assert.assertEquals(1, a.getCbo());
	}
	
	@Test
	public void countInterfacesAndInheritances() {
		
		CKClassResult b = report.get("cbo.Coupling2");
		Assert.assertEquals(5, b.getCbo());
	}

	@Test
	public void countClassCreations() {
		CKClassResult b = report.get("cbo.Coupling4");
		Assert.assertEquals(3, b.getCbo());
	}

	@Test
	public void countMethodParameters() {
		
		CKClassResult b = report.get("cbo.MethodParams");
		Assert.assertEquals(2, b.getCbo());
	}

	@Test
	public void methodLevel() {
		CKClassResult b = report.get("cbo.Coupling5");
		Assert.assertEquals(0, b.getMethod("am1/0").get().getCbo());
		Assert.assertEquals(1, b.getMethod("am2/0").get().getCbo());
		Assert.assertEquals(1, b.getMethod("am3/0").get().getCbo());
		Assert.assertEquals(2, b.getMethod("am4/0").get().getCbo());

	}
}
