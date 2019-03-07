package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CKClassResult;

import java.util.Map;

public class FieldsTest extends BaseTest {


	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/fields");
	}
	
	@Test
	public void all() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(5, a.getNof());
	}

	@Test
	public void allPublic() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(2, a.getNopf());
	}

	@Test
	public void allStatic() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(2, a.getNosf());
	}
}
