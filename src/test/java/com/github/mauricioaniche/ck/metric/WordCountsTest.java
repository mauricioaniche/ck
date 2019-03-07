package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class WordCountsTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/wordcounts");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("wordcounts.WordCounts");

		Assert.assertEquals(10, a.getUniqueWordsQty());

		Assert.assertEquals(1, a.getMethod("m1/0").get().getUniqueWordsQty());
		Assert.assertEquals(7, a.getMethod("m2/0").get().getUniqueWordsQty());
	}
}
