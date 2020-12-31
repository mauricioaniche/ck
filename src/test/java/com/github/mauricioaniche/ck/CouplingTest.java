package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CouplingTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/cbo");
	}
	
	@Test
	public void ignoreJavaTypes() {
		CKClassResult a = report.get("cbo.Coupling0");
		Assertions.assertEquals(0, a.getCboModified());
		Assertions.assertEquals(0, a.getFanin());
		Assertions.assertEquals(0, a.getFanout());
	}
	
	@Test
	public void countDifferentPossibilitiesOfDependencies() {
		CKClassResult a = report.get("cbo.Coupling1");
		Assertions.assertEquals(6, a.getCboModified());
		Assertions.assertEquals(0, a.getFanin());
		Assertions.assertEquals(6, a.getFanout());
	}
	
	@Test
	public void countEvenWhenNotResolved() {
		
		CKClassResult a = report.get("cbo.Coupling3");
		Assertions.assertEquals(1, a.getCboModified());
		Assertions.assertEquals(0, a.getFanin());
		Assertions.assertEquals(1, a.getFanout());
	}
	
	@Test
	public void countInterfacesAndInheritances() {
		
		CKClassResult b = report.get("cbo.Coupling2");
		Assertions.assertEquals(5, b.getCboModified());
		Assertions.assertEquals(0, b.getFanin());
		Assertions.assertEquals(5, b.getFanout());
	}

	@Test
	public void countClassCreations() {
		CKClassResult b = report.get("cbo.Coupling4");
		Assertions.assertEquals(3, b.getCboModified());
		Assertions.assertEquals(0, b.getFanin());
		Assertions.assertEquals(3, b.getFanout());
	}

	@Test
	public void countMethodParameters() {
		
		CKClassResult b = report.get("cbo.MethodParams");
		Assertions.assertEquals(2, b.getCboModified());
		Assertions.assertEquals(0, b.getFanin());
		Assertions.assertEquals(2, b.getFanout());
	}

	@Test
	public void methodLevel() {
		CKClassResult a = report.get("cbo.A");
		CKClassResult b = report.get("cbo.B");
		CKClassResult c = report.get("cbo.Coupling5");
		
		Assertions.assertEquals(0, c.getMethod("am1/0").get().getCboModified());
		Assertions.assertEquals(1, c.getMethod("am2/0").get().getCboModified());
		Assertions.assertEquals(2, c.getMethod("am3/0").get().getCboModified());
		Assertions.assertEquals(3, c.getMethod("am4/0").get().getCboModified());
		
		Assertions.assertEquals(2, a.getMethod("method/0").get().getFanin());
		Assertions.assertEquals(2, b.getMethod("method/0").get().getFanin());
		
		Assertions.assertEquals(0, c.getMethod("am1/0").get().getFanout());
		Assertions.assertEquals(1, c.getMethod("am2/0").get().getFanout());
		Assertions.assertEquals(2, c.getMethod("am3/0").get().getFanout());
		Assertions.assertEquals(3, c.getMethod("am4/0").get().getFanout());
	}

	@Test
	public void fullOfNonResolvedTypes() {
		CKClassResult b = report.get("cbo.NotResolved");
		Assertions.assertEquals(5, b.getCboModified());
		Assertions.assertEquals(0, b.getFanin());
		Assertions.assertEquals(5, b.getFanout());
	}
	
	@Test
	public void countFanIn() {
		CKClassResult a = report.get("cbo.Coupling8");
		Assertions.assertEquals(2, a.getFanin());
		
		CKClassResult b = report.get("cbo.Coupling81");
		Assertions.assertEquals(1, b.getFanin());
		
		CKClassResult c = report.get("cbo.Coupling82");
		Assertions.assertEquals(0, c.getFanin());
		
		CKClassResult d = report.get("cbo.Coupling83");
		Assertions.assertEquals(1, d.getFanin());
	}
	
	@Test
	public void countFanOut() {
		CKClassResult a = report.get("cbo.Coupling8");
		Assertions.assertEquals(3, a.getFanout());
		
		CKClassResult b = report.get("cbo.Coupling81");
		Assertions.assertEquals(4, b.getFanout());
		
		CKClassResult c = report.get("cbo.Coupling82");
		Assertions.assertEquals(3, c.getFanout());
		
		CKClassResult d = report.get("cbo.Coupling83");
		Assertions.assertEquals(1, d.getFanout());
	}
	
	@Test
	public void countCBOModified() {
		CKClassResult a = report.get("cbo.Coupling8");
		Assertions.assertEquals(5, a.getCboModified());
		
		CKClassResult b = report.get("cbo.Coupling81");
		Assertions.assertEquals(5, b.getCboModified());
		
		CKClassResult c = report.get("cbo.Coupling82");
		Assertions.assertEquals(3, c.getCboModified());
		
		CKClassResult d = report.get("cbo.Coupling83");
		Assertions.assertEquals(2, d.getCboModified());
	}
	
}
