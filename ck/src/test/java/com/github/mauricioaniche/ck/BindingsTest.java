package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BindingsTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/bindings");
	}


	@Test
	public void fullNameEvenWhenTypesAreNotAvailable() {
		CKClassResult a = report.get("bind.BindingFail1");
		Assertions.assertNotNull(a);
	}

	@Test
	public void fullNameEvenWhenTypesAreNotAvailableNotEvenInImports() {
		CKClassResult a = report.get("bind.BindingFail2");
		Assertions.assertNotNull(a);
	}

}
