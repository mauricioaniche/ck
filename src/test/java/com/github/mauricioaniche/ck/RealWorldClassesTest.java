package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RealWorldClassesTest extends BaseTest {

	private static Map<String, CKClassResult> report;

	@BeforeClass
	public static void setUp() {
		report = run(fixturesDir() + "/real-world");
	}


	// This class was breaking, because CSVParser had anonymous types.
	// FIX was to ignore whenever an anonymous class, subclass, or lambda expression appears.
	@Test
	public void commonsCsvClass() {

		CKClassResult ck = report.get("debug.CSVParser");

		for (CKMethodResult CKMethodResult : ck.getMethods()) {
			System.out.println(CKMethodResult.getMethodName());

			for (Map.Entry<String, Integer> entry : CKMethodResult.getVariablesUsage().entrySet()) {
				System.out.println("- variable: " + entry.getKey());
			}
		}
		System.out.println(ck);
	}

	// This class contains a method with a huge javadoc, and then the LOC was getting too big for
	// a very small method. We use a better way to count LOC now, ignoring java comments.
	@Test
	public void commonsCsvClass2() {
		CKClassResult ck = report.get("org.apache.commons.csv.CSVFormat");

		Assert.assertEquals(3, ck.getMethod("isLineBreak/1[char]").get().getLoc());
		Assert.assertEquals(635, ck.getMethod("isLineBreak/1[char]").get().getStartLine());

		Assert.assertEquals(1575, ck.getMethod("withAllowMissingColumnNames/1[boolean]").get().getStartLine());
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

	// this was crashing because of a lambda expression in a field.
	// fix: consider the lambda as part of the class and/OR method it is embedded.
	@Test
	public void abstractSimpleHandler() {
		CKClassResult a = report.get("com.firefly.net.tcp.AbstractSimpleHandler");
		Assert.assertNotNull(a);
		Assert.assertEquals(3, a.getNumberOfMethods());
		Assert.assertEquals(3, a.getNumberOfFields());
	}

	// one more test case for the same case above. Redundant.
	@Test
	public void quotedQualityCSV() {
		CKClassResult a = report.get("com.firefly.codec.http2.model.QuotedQualityCSV");
		Assert.assertNotNull(a);
	}

	@Test
	public void beanificationTestCase() {
		Assert.assertTrue(report.keySet().contains("org.apache.commons.beanutils2.BeanificationTestCase$1TestIndependenceThread"));

		CKClassResult a = report.get("org.apache.commons.beanutils2.BeanificationTestCase$1TestIndependenceThread");
		Assert.assertNotNull(a);

		Assert.assertEquals(3, a.getNumberOfMethods());
		Assert.assertEquals("subclass", a.getType());
	}

	// this one contains subclasses
	@Test
	public void csvParser2() {
		CKClassResult class1 = report.get("org.apache.commons.csv.CSVParser2");
		CKClassResult class2 = report.get("org.apache.commons.csv.CSVParser2$CSVRecordIterator");

		Assert.assertNotNull(class1);
		Assert.assertNotNull(class2);

		Assert.assertEquals(4, class2.getMethods().size());

		Set<String> allMethodsInC2 = class2.getMethods().stream().map(x -> x.getMethodName()).collect(Collectors.toSet());
		Assert.assertTrue(allMethodsInC2.contains("hasNext/0"));
		Assert.assertTrue(allMethodsInC2.contains("getNextRecord/0"));
		Assert.assertTrue(allMethodsInC2.contains("next/0"));
		Assert.assertTrue(allMethodsInC2.contains("remove/0"));

		Set<String> allMethodsInC1 = class1.getMethods().stream().map(x -> x.getMethodName()).collect(Collectors.toSet());
		Assert.assertTrue(allMethodsInC1.contains("createHeaderMap/0"));
	}

}
