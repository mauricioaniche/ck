package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class WordCountsTest extends BaseTest {

	private static Map<String, CKClassResult> report;
	private CKClassResult w1;
	private CKClassResult w2;
	private CKClassResult w3;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/wordcounts");
	}
	
	@Before
	public void getClasses() {
		this.w1 = report.get("wordcounts.WordCounts");
		this.w2 = report.get("wordcounts.WordCounts2");
		this.w3 = report.get("wordcounts.WordCounts3");
	}

	@Test
	public void count() {
		Assert.assertEquals(1, w1.getMethod("m1/0").get().getUniqueWordsQty());
		Assert.assertEquals(7, w1.getMethod("m2/0").get().getUniqueWordsQty());
	}

	// related to issue #33
	@Test
	public void countStaticInitializer() {
		Assert.assertEquals(1, w2.getMethod("m1/0").get().getUniqueWordsQty());
		Assert.assertEquals(7, w2.getMethod("m2/0").get().getUniqueWordsQty());
		Assert.assertEquals(3, w2.getMethod("(initializer)").get().getUniqueWordsQty());
	}

	@Test
	public void countAtClassLevel() {
		Assert.assertEquals(10, w1.getUniqueWordsQty());
		Assert.assertEquals(13, w2.getUniqueWordsQty());
	}

	// related to issue #34
	@Test
	public void subclasses() {
		Assert.assertEquals(7, w3.getMethod("m2/0").get().getUniqueWordsQty());
		Assert.assertEquals(10, w3.getUniqueWordsQty());

		// numbers in the subclass
		CKClassResult subclass = report.get("wordcounts.WordCounts3$1X");
		Assert.assertEquals(3, subclass.getMethod("xxx/0").get().getUniqueWordsQty());
		Assert.assertEquals(4, subclass.getUniqueWordsQty());

		CKClassResult subclass2 = report.get("wordcounts.WordCounts3$Y");
		Assert.assertEquals(2, subclass2.getMethod("yyy/0").get().getUniqueWordsQty());
		Assert.assertEquals(3, subclass2.getUniqueWordsQty());
	}
}
