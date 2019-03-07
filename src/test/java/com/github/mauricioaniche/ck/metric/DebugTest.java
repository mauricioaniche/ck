package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class DebugTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/debug");
	}

	// This class was breaking, because CSVParser had anonymous types.
	// FIX was to ignore whenever an anonymous class, subclass, or lambda expression appears.
	@Test
	public void commonsCsvClass() {

		CKClassResult ck = report.get("debug.CSVParser");

		for (CKMethodResult CKMethodResult : ck.getMethods()) {
			System.out.println(CKMethodResult.getMethodName());

			for (Map.Entry<String, Integer> entry : CKMethodResult.getVariablesUsage().entrySet()) {
				System.out.println("- variable: " + entry.getKey());
			}
		}
		System.out.println(ck);
	}
}
