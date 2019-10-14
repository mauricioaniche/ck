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
		Assert.assertEquals(8, a.getNumberOfFields());
	}

	@Test
	public void allPublic() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(2, a.getNumberOfPublicFields());
	}

	@Test
	public void allStatic() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(3, a.getNumberOfStaticFields());
	}


	@Test
	public void allPrivate() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(4, a.getNumberOfPrivateFields());
	}


	@Test
	public void allDefault() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(2, a.getNumberOfDefaultFields());
	}

	@Test
	public void allSynchronized() {
		CKClassResult a = report.get("fields.Fields");
		Assert.assertEquals(1, a.getNumberOfSynchronizedFields());
	}
}
