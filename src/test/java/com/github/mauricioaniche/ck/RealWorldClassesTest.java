package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

public class RealWorldClassesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/real-world");
	}

	// this one always worked
	@Test
	public void xmlInputFactory() {

		CKClassResult a = report.get("javax.xml.stream.XMLInputFactory");
		Assert.assertEquals(29, a.getNumberOfMethods());

		Optional<CKMethodResult> m1 = a.getMethod("getXMLReporter/0");
		Assert.assertTrue(org.eclipse.jdt.core.dom.Modifier.isAbstract(m1.get().getModifiers()));
	}

	// in this one, a method defined inside an anonymous class, instantiated at
	// a field was throwing an exception. The resolveBinding() was not really able
	// to resolve the binding.
	// Solution: in the endVisit(MethodDeclaration), if there is no method, skip it.
	@Test
	public void factoryFinder() {

		CKClassResult a = report.get("javax.xml.ws.spi.FactoryFinder");
		Assert.assertEquals(3, a.getNumberOfMethods());
	}

	// this was crashing, also related to the bug explained in the
	// 'factoryFinder' test. Test a bit redundant.
	@Test
	public void xmlOutputFactory() {
		CKClassResult a = report.get("javax.xml.stream.XMLOutputFactory");
		Assert.assertEquals(14, a.getNumberOfMethods());
	}

	// this was crashing, also related to the bug explained in the
	// 'factoryFinder' test. Test a bit redundant.
	@Test
	public void feature() {
		CKClassResult a = report.get("com.hry.spring.grpc.stream.Feature");
		Assert.assertNotNull(a);

		Optional<CKMethodResult> m1 = a.getMethod("getNameBytes/0");
		Assert.assertNotNull(m1);
	}

	// the number of methods assertion below should be 2.
	// Before, CK was returning 1, because a resolveBinding was failing.
	// We need the JARs here to solve the bindings correctly.
	// CK now tries binding first and, if it fails, it resorts
	// to the information you can get at the class only.
	@Test
	public void greeterGrpc() {
		CKClassResult a = report.get("com.hry.spring.grpc.simple.GreeterGrpc");
		Assert.assertNotNull(a);
		CKClassResult b = report.get("com.hry.spring.grpc.simple.GreeterGrpc$GreeterImplBase");
		Assert.assertNotNull(b);

		Assert.assertEquals(2, b.getNumberOfMethods());

		CKClassResult c = report.get("com.hry.spring.grpc.simple.GreeterGrpc$GreeterBlockingStub");
		Assert.assertNotNull(c);
	}

}
