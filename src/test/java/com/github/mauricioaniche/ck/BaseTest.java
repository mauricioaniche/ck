package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public abstract class BaseTest {


	static Set<Method> classGetters = Arrays.stream(CKClassResult.class.getMethods())
			.filter(m -> m.getName().startsWith("get"))
			.filter(m -> !m.getName().equals("getModifiers")) // it can be -1 if it's anonymous
			.filter(m -> m.getParameterCount() == 0)
			.collect(Collectors.toSet());

	static Set<Method> methodGetters = Arrays.stream(CKMethodResult.class.getMethods())
			.filter(m -> m.getName().startsWith("get"))
			.filter(m -> !m.getName().equals("getModifiers"))
			.filter(m -> m.getParameterCount() == 0)
			.collect(Collectors.toSet());


	protected Map<String, CKClassResult> report;

	protected static String fixturesDir() {
		try {
			String cfgFile = new File(BaseTest.class.getResource("/").getPath() + "../../fixtures/").getCanonicalPath();
			return cfgFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void checkNulls() throws InvocationTargetException, IllegalAccessException {
		if(report==null)
			return;

		for(String reportedClass : report.keySet()) {
			// no nulls in getters of the class
			CKClassResult ckClassResult = report.get(reportedClass);
			for(Method method : classGetters) {
				Object result = method.invoke(ckClassResult);
				Assertions.assertNotNull(result, method.getName() + " is null");

				// integer >= 0
				if(method.getReturnType().equals(int.class)) {
					int intResult = (int) result;
					Assertions.assertTrue(intResult >= 0, method.getName() + " < 0");
				}
			}

			Set<CKMethodResult> ckMethods = ckClassResult.getMethods();
			// no nulls in getters of the class
			for (CKMethodResult ckMethodResult : ckMethods) {
				for(Method method : methodGetters) {
					Object result = method.invoke(ckMethodResult);
					Assertions.assertNotNull(result, method.getName() + " is null");

					// integer >= 0
					if(method.getReturnType().equals(int.class)) {
						int intResult = (int) result;
						Assertions.assertTrue(intResult >= 0, method.getName() + " < 0");
					}

				}
			}
		}


	}

	protected static Map<String, CKClassResult> run(String dir) {
		Map<String, CKClassResult> map = new HashMap<>();
		new CK().calculate(dir, result -> map.put(result.getClassName(), result));
		return map;
	}

	protected static Map<String, CKClassResult> runDebug(String dir) {
		return runDebug(dir, () -> Arrays.asList(new ASTDebugger()), () -> Collections.emptyList());
	}

	protected static Map<String, CKClassResult> runDebug(String dir, Callable<List<ClassLevelMetric>> classLevelMetrics, Callable<List<MethodLevelMetric>> methodLevelMetrics) {
		Map<String, CKClassResult> map = new HashMap<>();
		new CK(classLevelMetrics, methodLevelMetrics).calculate(dir, result -> map.put(result.getClassName(), result));
		return map;
	}
}
