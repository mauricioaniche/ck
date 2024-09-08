package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FieldUsageTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/fieldusage");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("fieldusage.FieldUsage");

		CKMethodResult m1 = a.getMethod("m1/0").get();
		Map<String, Integer> m1Fields = m1.getFieldUsage();
		Assertions.assertEquals(2, m1Fields.get("a").intValue());
		Assertions.assertEquals(1, m1Fields.get("b").intValue());

		CKMethodResult m2 = a.getMethod("m2/0").get();
		Map<String, Integer> m2Fields = m2.getFieldUsage();
		Assertions.assertEquals(1, m2Fields.get("a").intValue());

		CKMethodResult m3 = a.getMethod("m3/0").get();
		Map<String, Integer> m3Fields = m3.getFieldUsage();
		Assertions.assertEquals(2, m3Fields.get("a").intValue());
	}

	@Test
	public void fieldsThatAreDeclaredAfter() {
		CKClassResult a = report.get("fieldusage.FieldUsage");

		CKMethodResult m4 = a.getMethod("m4/0").get();
		Assertions.assertEquals(2, m4.getFieldUsage().get("xx").intValue());
	}

	@Test
	public void lambdaFunction(){
		CKClassResult a = report.get("fieldusage.FieldUsage");
		CKMethodResult m5 = a.getMethod("m5/0").get();
		Assertions.assertEquals(1, m5.getFieldUsage().get("b").intValue());
	}

	@Test
	public void ifBlock() {
		CKClassResult a = report.get("fieldusage.FieldUsage");
		CKMethodResult m6 = a.getMethod("m6/0").get();
		Assertions.assertEquals(2, m6.getFieldUsage().get("a").intValue());
		Assertions.assertEquals(1, m6.getFieldUsage().get("b").intValue());
		Assertions.assertEquals(1, m6.getFieldUsage().get("c").intValue());
	}

	@Test
	public void returnStatement() {
		CKClassResult a = report.get("fieldusage.FieldUsage");
		CKMethodResult m7 = a.getMethod("m7/0").get();
		Assertions.assertEquals(1, m7.getFieldUsage().get("a").intValue());
		Assertions.assertEquals(1, m7.getFieldUsage().get("b").intValue());
	}

	@Test
	public void nonLocalField() {
		CKClassResult a = report.get("fieldusage.FieldUsage");
		CKMethodResult m8 = a.getMethod("m8/0").get();
		Assertions.assertEquals(1, m8.getFieldUsage().get("b").intValue());
	}
}