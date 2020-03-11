package com.github.mauricioaniche.ck.realworld;

import com.github.mauricioaniche.ck.BaseTest;
import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class HugeRealWorldClassTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/real-world-huge-class");
	}

	// testing whether CK crashes if the class is absurdly huge!
	@Test
	public void hugeClass() {
		CKClassResult class1 = report.get("com.satoshilabs.trezor.lib.protobuf.TrezorMessage");

		Assert.assertNotNull(class1);
	}
}
