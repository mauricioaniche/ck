package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.*;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordCountsTest extends BaseTest {

	private CKClassResult w1;
	private CKClassResult w2;
	private CKClassResult w3;

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/wordcounts");
	}
	
	@BeforeEach
	public void getClasses() {
		this.w1 = report.get("wordcounts.WordCounts");
		this.w2 = report.get("wordcounts.WordCounts2");
		this.w3 = report.get("wordcounts.WordCounts3");
	}

	@Test
	public void count() {
		Assertions.assertEquals(1, w1.getMethod("m1/0").get().getUniqueWordsQty());
		Assertions.assertEquals(7, w1.getMethod("m2/0").get().getUniqueWordsQty());
	}

	// related to issue #33
	@Test
	public void countStaticInitializer() {
		Assertions.assertEquals(1, w2.getMethod("m1/0").get().getUniqueWordsQty());
		Assertions.assertEquals(7, w2.getMethod("m2/0").get().getUniqueWordsQty());
		Assertions.assertEquals(3, w2.getMethod("(initializer 1)").get().getUniqueWordsQty());
	}

	@Test
	public void countAtClassLevel() {
		Assertions.assertEquals(10, w1.getUniqueWordsQty());
		Assertions.assertEquals(13, w2.getUniqueWordsQty());
	}

	// related to issue #34
	@Test
	public void subclasses() {
		Assertions.assertEquals(7, w3.getMethod("m2/0").get().getUniqueWordsQty());
		Assertions.assertEquals(10, w3.getUniqueWordsQty());

		// numbers in the subclass
		CKClassResult subclass = report.get("wordcounts.WordCounts3$1X");
		Assertions.assertEquals(3, subclass.getMethod("xxx/0").get().getUniqueWordsQty());
		Assertions.assertEquals(4, subclass.getUniqueWordsQty());

		CKClassResult subclass2 = report.get("wordcounts.WordCounts3$Y");
		Assertions.assertEquals(2, subclass2.getMethod("yyy/0").get().getUniqueWordsQty());
		Assertions.assertEquals(3, subclass2.getUniqueWordsQty());
	}
}
