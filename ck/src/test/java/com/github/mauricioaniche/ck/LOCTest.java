package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LOCTest extends BaseTest {

	@Test
	public void countLinesIgnoringEmptyLines() {
		Map<String, CKClassResult> report = run(fixturesDir() + "/cbo");

		CKClassResult a = report.get("cbo.Coupling1");

		// note that the package line should not count here
		Assertions.assertEquals(10, a.getLoc());
	}

	@Test
	public void countLinesForInnerClasses() {
		Map<String, CKClassResult> report = run(fixturesDir() + "/innerclasses");

		CKClassResult a = report.get("innerclasses.MessyClass");
		Assertions.assertEquals(65, a.getLoc());

		CKClassResult sc1 = report.get("innerclasses.MessyClass$InnerClass1");
		Assertions.assertEquals(14, sc1.getLoc());

		CKClassResult sc2 = report.get("innerclasses.MessyClass$InnerClass2");
		Assertions.assertEquals(9, sc2.getLoc());

		CKClassResult an1 = report.get("innerclasses.MessyClass$Anonymous1");
		Assertions.assertEquals(5, an1.getLoc());
	}
	
}
