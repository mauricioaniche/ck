package com.github.mauricioaniche.ck.realworld;

import com.github.mauricioaniche.ck.BaseTest;
import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.eclipse.jdt.core.dom.Modifier.isStatic;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RealWorldClassesTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/real-world");
	}


	// This class was breaking, because CSVParser had anonymous types.
	// FIX was to ignore whenever an anonymous class, subclass, or lambda expression appears.
	@Test
	public void commonsCsvClass() {

		CKClassResult ck = report.get("debug.CSVParser");

		for (com.github.mauricioaniche.ck.CKMethodResult CKMethodResult : ck.getMethods()) {

			for (Map.Entry<String, Integer> entry : CKMethodResult.getVariablesUsage().entrySet()) {
				System.out.println("- variable: " + entry.getKey());
			}
		}
	}

	// This class contains a method with a huge javadoc, and then the LOC was getting too big for
	// a very small method. We use a better way to count LOC now, ignoring java comments.
	@Test
	public void commonsCsvClass2() {
		CKClassResult ck = report.get("org.apache.commons.csv.CSVFormat");

		Assertions.assertEquals(3, ck.getMethod("isLineBreak/1[char]").get().getLoc());
		Assertions.assertEquals(635, ck.getMethod("isLineBreak/1[char]").get().getStartLine());

		Assertions.assertEquals(1575, ck.getMethod("withAllowMissingColumnNames/1[boolean]").get().getStartLine());
	}

	// this one always worked
	@Test
	public void xmlInputFactory() {

		CKClassResult a = report.get("javax.xml.stream.XMLInputFactory");
		Assertions.assertEquals(29, a.getNumberOfMethods());

		Optional<CKMethodResult> m1 = a.getMethod("getXMLReporter/0");
		Assertions.assertTrue(org.eclipse.jdt.core.dom.Modifier.isAbstract(m1.get().getModifiers()));
	}

	// in this one, a method defined inside an anonymous class, instantiated at
	// a field was throwing an exception. The resolveBinding() was not really able
	// to resolve the binding.
	// Solution: in the endVisit(MethodDeclaration), if there is no method, skip it.
	@Test
	public void factoryFinder() {

		CKClassResult a = report.get("javax.xml.ws.spi.FactoryFinder");
		Assertions.assertEquals(3, a.getNumberOfMethods());
	}

	// this was crashing, also related to the bug explained in the
	// 'factoryFinder' test. Test a bit redundant.
	@Test
	public void xmlOutputFactory() {
		CKClassResult a = report.get("javax.xml.stream.XMLOutputFactory");
		Assertions.assertEquals(14, a.getNumberOfMethods());
	}

	// this was crashing, also related to the bug explained in the
	// 'factoryFinder' test. Test a bit redundant.
	@Test
	public void feature() {
		CKClassResult a = report.get("com.hry.spring.grpc.stream.Feature");
		Assertions.assertNotNull(a);

		Optional<CKMethodResult> m1 = a.getMethod("getNameBytes/0");
		Assertions.assertNotNull(m1);
	}

	// the number of methods assertion below should be 2.
	// Before, CK was returning 1, because a resolveBinding was failing.
	// We need the JARs here to solve the bindings correctly.
	// CK now tries binding first and, if it fails, it resorts
	// to the information you can get at the class only.
	@Test
	public void greeterGrpc() {
		CKClassResult a = report.get("com.hry.spring.grpc.simple.GreeterGrpc");
		Assertions.assertNotNull(a);
		CKClassResult b = report.get("com.hry.spring.grpc.simple.GreeterGrpc$GreeterImplBase");
		Assertions.assertNotNull(b);

		Assertions.assertEquals(2, b.getNumberOfMethods());

		CKClassResult c = report.get("com.hry.spring.grpc.simple.GreeterGrpc$GreeterBlockingStub");
		Assertions.assertNotNull(c);
	}

	// this was crashing because of a lambda expression in a field.
	// fix: consider the lambda as part of the class and/OR method it is embedded.
	@Test
	public void abstractSimpleHandler() {
		CKClassResult a = report.get("com.firefly.net.tcp.AbstractSimpleHandler");
		Assertions.assertNotNull(a);
		Assertions.assertEquals(3, a.getNumberOfMethods());
		Assertions.assertEquals(3, a.getNumberOfFields());
	}

	// one more test case for the same case above. Redundant.
	@Test
	public void quotedQualityCSV() {
		CKClassResult a = report.get("com.firefly.codec.http2.model.QuotedQualityCSV");
		Assertions.assertNotNull(a);
	}

	@Test
	public void beanificationTestCase() {
		Assertions.assertTrue(report.keySet().contains("org.apache.commons.beanutils2.BeanificationTestCase$1TestIndependenceThread"));

		CKClassResult a = report.get("org.apache.commons.beanutils2.BeanificationTestCase$1TestIndependenceThread");
		Assertions.assertNotNull(a);

		Assertions.assertEquals(3, a.getNumberOfMethods());
		Assertions.assertEquals("innerclass", a.getType());
	}

	// this one contains subclasses
	@Test
	public void csvParser2() {
		CKClassResult class1 = report.get("org.apache.commons.csv.CSVParser2");
		CKClassResult class2 = report.get("org.apache.commons.csv.CSVParser2$CSVRecordIterator");

		Assertions.assertNotNull(class1);
		Assertions.assertNotNull(class2);

		Assertions.assertEquals(4, class2.getMethods().size());

		Set<String> allMethodsInC2 = class2.getMethods().stream().map(x -> x.getMethodName()).collect(Collectors.toSet());
		Assertions.assertTrue(allMethodsInC2.contains("hasNext/0"));
		Assertions.assertTrue(allMethodsInC2.contains("getNextRecord/0"));
		Assertions.assertTrue(allMethodsInC2.contains("next/0"));
		Assertions.assertTrue(allMethodsInC2.contains("remove/0"));

		Set<String> allMethodsInC1 = class1.getMethods().stream().map(x -> x.getMethodName()).collect(Collectors.toSet());
		Assertions.assertTrue(allMethodsInC1.contains("createHeaderMap/0"));
	}

	// one more long class with static subclasses
	// just double checking it finds all the subclasses
	@Test
	public void executorUtils() {
		CKClassResult class1 = report.get("org.apache.commons.lang.functor.ExecutorUtils");
		Assertions.assertNotNull(class1);
		Assertions.assertFalse(isStatic(class1.getModifiers()));

		CKClassResult class2 = report.get("org.apache.commons.lang.functor.ExecutorUtils$ChainedExecutor");
		Assertions.assertNotNull(class2);
		Assertions.assertTrue(isStatic(class2.getModifiers()));

		CKClassResult class3 = report.get("org.apache.commons.lang.functor.ExecutorUtils$SwitchExecutor");
		Assertions.assertNotNull(class3);
		Assertions.assertTrue(isStatic(class3.getModifiers()));

		CKClassResult class4 = report.get("org.apache.commons.lang.functor.ExecutorUtils$ForExecutor");
		Assertions.assertNotNull(class4);
		Assertions.assertTrue(isStatic(class4.getModifiers()));

		CKClassResult class5 = report.get("org.apache.commons.lang.functor.ExecutorUtils$WhileExecutor");
		Assertions.assertNotNull(class5);
		Assertions.assertTrue(isStatic(class5.getModifiers()));

	}

	// check whether the kerberosChallenge method is detected
	// in one of the versions of the project, it identifies the method with a wrong arity.
	// TODO: find this version.
	@Test
	public void asyncHttpClient_1() {
		CKClassResult class1 = report.get("org.asynchttpclient.netty.handler.intercept.Unauthorized401Interceptor");

		CKMethodResult method = class1.getMethod("kerberosChallenge/3[org.asynchttpclient.netty.handler.intercept.Realm,org.asynchttpclient.netty.handler.intercept.Request,org.asynchttpclient.netty.handler.intercept.HttpHeaders]").get();
		Assertions.assertNotNull(method);
	}

	@Test
	public void asyncHttpClient_2() {
		CKClassResult class1 = report.get("com.ning.http.client.providers.NettyAsyncHttpProvider");

		Assertions.assertNotNull(class1.getMethod("doConnect/3[com.ning.http.client.providers.Request,com.ning.http.client.providers.AsyncHandler<T>,com.ning.http.client.providers.NettyResponseFuture<T>]"));
	}

	// illustrates the example of https://github.com/mauricioaniche/ck/issues/54
	// still to be implemented
	@Test
	public void hectorPolicyManager_betterNamesForAnonymousClasses() {
		CKClassResult class1 = report.get("net.retakethe.policyauction.data.impl.HectorPolicyManagerImpl");
		CKClassResult anonymousClass = report.get("net.retakethe.policyauction.data.impl.HectorPolicyManagerImpl$Anonymous1");

		Assertions.assertNotNull(class1);
		Assertions.assertNotNull(anonymousClass);
	}

	/**
	 * An extra test to make sure it gets the static classes declared in here.
	 * Trying to reproduce a bug we observed in a client.
	 */
	@Test
	void ambry_ByteBufferAsyncWritableChannel() {
		CKClassResult class1 = report.get("com.github.ambry.commons.ByteBufferAsyncWritableChannel$ChunkData");
		Assertions.assertNotNull(class1);

		Assertions.assertEquals(2, class1.getMethods().size());

		Assertions.assertNotNull(class1.getMethod("ChunkData/2[java.nio.ByteBuffer,com.github.ambry.commons.Callback<java.lang.Long>]"));
		Assertions.assertNotNull(class1.getMethod("resolveChunk/1[java.lang.Exception]"));
	}

	/**
	 * Tests if the for anonymous classes and classes with no visible methods methods invocations and local method invocations are generated.
	 * For more details see issue #65.
	 */
	@Test
	public void methodInvocationsInnerClass(){
		CKClassResult class1 = report.get("com.github.ambry.commons.ByteBufferAsyncWritableChannel$ChunkData");
		CKMethodResult constructorMethod = class1.getMethod("ChunkData/2[java.nio.ByteBuffer,com.github.ambry.commons.Callback<java.lang.Long>]").get();

		Assertions.assertEquals(1, constructorMethod.getMethodInvocations().size());
		Assertions.assertEquals(0, constructorMethod.getMethodInvocationsLocal().size());
		Assertions.assertEquals(0, constructorMethod.getMethodInvocationsIndirectLocal().size());
	}
}
