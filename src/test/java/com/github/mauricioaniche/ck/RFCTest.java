package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.*;

import java.util.Map;

@DisplayName("Counts the number of unique method invocations in a class")
public class RFCTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeAll
	public static void setUp() {
		report = run(fixturesDir() + "/rfc");
	}

	@Test
	@DisplayName("Counts the number of methods invocations")
	public void countMethodInvocations() {
		CKClassResult a = report.get("rfc.GO");
		Assertions.assertEquals(3, a.getRfc());
	}

	@Test
	@DisplayName("Counts the number of methods invocations at method level")
	public void methodLevel() {
		CKClassResult a = report.get("rfc.GO");
		Assertions.assertEquals(2, a.getMethod("m1/0").get().getRfc());
		Assertions.assertEquals(0, a.getMethod("m2/1[int]").get().getRfc());
		Assertions.assertEquals(1, a.getMethod("m3/0").get().getRfc());
	}

	@Test
	@DisplayName("Counts the number of super methods invocations")
	public void countSuperInvocations() {
		CKClassResult a = report.get("rfc.GO3");
		Assertions.assertEquals(2, a.getRfc());
	}

	@Test
	@Disabled
	@DisplayName("It isn't possible differentiate types with status analysis")
	public void notPossibleToDifferentiateTypesWithStaticAnalysis() {
		CKClassResult a = report.get("rfc.RFC3");
		Assertions.assertEquals(2, a.getRfc());
	}

	@Test
	@DisplayName("Shouldn't count constructor invocations")
	public void doesNotCountConstructorInvocations() {
		CKClassResult a = report.get("rfc.RFC2");
		Assertions.assertEquals(0, a.getRfc());
	}

	@Test
	@DisplayName("No method invocation")
	public void noMethodInvocation() {
		CKClassResult a = report.get("rfc.RFC4");
		Assertions.assertEquals(0, a.getRfc());
	}

	//  TODO Should be 2 method invocations, "fobj.dummyFun(5);" and "z()"
	@Test
	@DisplayName("Counts the number of functional method invocation")
	public void functionalInterface() {
		CKClassResult a = report.get("rfc.RFC5");
		Assertions.assertEquals(3, a.getRfc());
	}

	@Test
	@DisplayName("Counts the number of method invocations in a sequence of calls")
	public void test1() {
		CKClassResult a = report.get("rfc.RFC6");
		Assertions.assertEquals(4, a.getRfc());
	}

	@Test
	@DisplayName("Counts the number of method reference invocations")
	public void test2() {
		CKClassResult a = report.get("rfc.RFC7");
		Assertions.assertEquals(4, a.getMethod("m1/0").get().getRfc());
		Assertions.assertEquals(8, a.getMethod("m2/0").get().getRfc());
	}
}