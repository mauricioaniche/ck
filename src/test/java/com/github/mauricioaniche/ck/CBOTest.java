package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CBOTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeAll
	public static void setUp() {
		report = run(fixturesDir() + "/cbo");
	}
	
	@Test
	public void ignoreJavaTypes() {
		CKClassResult a = report.get("cbo.Coupling0");
		Assertions.assertEquals(0, a.getCbo());
	}
	
	@Test
	public void countDifferentPossibilitiesOfDependencies() {
		
		CKClassResult a = report.get("cbo.Coupling1");
		Assertions.assertEquals(6, a.getCbo());
	}
	
	@Test
	public void countEvenWhenNotResolved() {
		
		CKClassResult a = report.get("cbo.Coupling3");
		Assertions.assertEquals(1, a.getCbo());
	}
	
	@Test
	public void countInterfacesAndInheritances() {
		
		CKClassResult b = report.get("cbo.Coupling2");
		Assertions.assertEquals(5, b.getCbo());
	}

	@Test
	public void countClassCreations() {
		CKClassResult b = report.get("cbo.Coupling4");
		Assertions.assertEquals(3, b.getCbo());
	}

	@Test
	public void countMethodParameters() {
		
		CKClassResult b = report.get("cbo.MethodParams");
		Assertions.assertEquals(2, b.getCbo());
	}

	@Test
	public void methodLevel() {
		CKClassResult b = report.get("cbo.Coupling5");
		Assertions.assertEquals(0, b.getMethod("am1/0").get().getCbo());
		Assertions.assertEquals(1, b.getMethod("am2/0").get().getCbo());
		Assertions.assertEquals(1, b.getMethod("am3/0").get().getCbo());
		Assertions.assertEquals(2, b.getMethod("am4/0").get().getCbo());
	}

	@Test
	public void fullOfNonResolvedTypes() {
		CKClassResult b = report.get("cbo.NotResolved");
		Assertions.assertEquals(5, b.getCbo());
	}

	// based on issue #43
	@Test
	public void couplingWithGenericsAndJavaType() {
		CKClassResult b = report.get("cbo.Coupling6");
		Assertions.assertEquals(1, b.getCbo());

		CKClassResult c = report.get("cbo.Coupling61");
		Assertions.assertEquals(1, c.getCbo());

		CKClassResult d = report.get("cbo.Coupling62");
		Assertions.assertEquals(2, d.getCbo());

		CKClassResult e = report.get("cbo.Coupling63");
		Assertions.assertEquals(2, e.getCbo());

		CKClassResult f = report.get("cbo.Coupling64");
		Assertions.assertEquals(2, f.getCbo());

		CKClassResult g = report.get("cbo.Coupling65");
		Assertions.assertEquals(2, g.getCbo());

	}
}
