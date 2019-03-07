package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class DebugTest extends BaseTest {

	private static Map<String, CKNumber> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/debug");
	}

	// This class was breaking, because CSVParser had anonymous types.
	// FIX was to ignore whenever an anonymous class, subclass, or lambda expression appears.
	@Test
	public void commonsCsvClass() {

		CKNumber ck = report.get("debug.CSVParser");

		for (MethodMetric methodMetric : ck.getMethods()) {
			System.out.println(methodMetric.getMethodName());

			for (Map.Entry<String, Integer> entry : methodMetric.getVariablesUsage().entrySet()) {
				System.out.println("- variable: " + entry.getKey());
			}
		}
		System.out.println(ck);
	}
}
