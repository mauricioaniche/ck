package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.junit.Assert;
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

	// This class contains a method with a huge javadoc, and then the LOC was getting too big for
	// a very small method. We use a better way to count LOC now, ignoring java comments.
	@Test
	public void commonsCsvClass2() {
		CKClassResult ck = report.get("org.apache.commons.csv.CSVFormat");

		Assert.assertEquals(3, ck.getMethod("isLineBreak/1[char]").get().getLoc());
		Assert.assertEquals(635, ck.getMethod("isLineBreak/1[char]").get().getStartLine());

		Assert.assertEquals(1575, ck.getMethod("withAllowMissingColumnNames/1[boolean]").get().getStartLine());
	}
}
