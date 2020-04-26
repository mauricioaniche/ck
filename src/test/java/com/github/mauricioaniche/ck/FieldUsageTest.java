package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FieldUsageTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeAll
	public static void setUp() {
		report = run(fixturesDir() + "/fieldusage");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("fieldusage.FieldUsage");

		CKMethodResult m1 = a.getMethod("m1/0").get();
		Map<String, Integer> m1Fields = m1.getFieldUsage();
		Assertions.assertEquals(2, m1Fields.get("a").intValue());
		Assertions.assertEquals(1, m1Fields.get("b").intValue());

		CKMethodResult m3 = a.getMethod("m3/0").get();
		Map<String, Integer> m3Fields = m3.getFieldUsage();
		Assertions.assertEquals(2, m3Fields.get("a").intValue());

		CKMethodResult m2 = a.getMethod("m2/0").get();
		Map<String, Integer> m2Fields = m2.getFieldUsage();
		Assertions.assertEquals(1, m2Fields.get("a").intValue());
	}

	@Test
	public void fieldsThatAreDeclaredAfter() {
		CKClassResult a = report.get("fieldusage.FieldUsage");

		CKMethodResult m4 = a.getMethod("m4/0").get();
		Map<String, Integer> m2Fields = m4.getFieldUsage();
		Assertions.assertEquals(2, m2Fields.get("xx").intValue());
	}
}
