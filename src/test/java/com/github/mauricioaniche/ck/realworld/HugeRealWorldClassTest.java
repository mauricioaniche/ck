package com.github.mauricioaniche.ck.realworld;

import com.github.mauricioaniche.ck.BaseTest;
import com.github.mauricioaniche.ck.CKClassResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HugeRealWorldClassTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/real-world-huge-class");
	}

	// testing whether CK crashes if the class is absurdly huge!
	@Test
	public void hugeClass() {
		CKClassResult class1 = report.get("com.satoshilabs.trezor.lib.protobuf.TrezorMessage");

		Assertions.assertNotNull(class1);
	}
}
