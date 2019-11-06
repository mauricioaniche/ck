package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class LOCTest extends BaseTest {

	@Test
	public void countLinesIgnoringEmptyLines() {
		Map<String, CKClassResult> report = run(fixturesDir() + "/cbo");

		CKClassResult a = report.get("cbo.Coupling1");
		Assert.assertEquals(11, a.getLoc());
	}

	@Test
	public void countLinesForSubClasses() {
		Map<String, CKClassResult> report = run(fixturesDir() + "/subclasses");

		CKClassResult a = report.get("subclasses.MessyClass");
		Assert.assertEquals(66, a.getLoc());

		CKClassResult sc1 = report.get("subclasses.MessyClass$SubClass1");
		Assert.assertEquals(15, sc1.getLoc());

		CKClassResult sc2 = report.get("subclasses.MessyClass$SubClass2");
		Assert.assertEquals(10, sc2.getLoc());

		CKClassResult an1 = report.get("subclasses.MessyClass$Anonymous1");
		Assert.assertEquals(6, an1.getLoc());
	}
	
}
