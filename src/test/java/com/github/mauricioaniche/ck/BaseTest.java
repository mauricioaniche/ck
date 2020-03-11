package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public abstract class BaseTest {

	protected static String fixturesDir() {
		try {
			String cfgFile = new File(BaseTest.class.getResource("/").getPath() + "../../fixtures/").getCanonicalPath();
			return cfgFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected static Map<String, CKClassResult> runDebug(String dir) {
		return run(dir, () -> Arrays.asList(new ASTDebugger()), () -> Collections.emptyList());
	}

	protected static Map<String, CKClassResult> run(String dir) {
		Map<String, CKClassResult> map = new HashMap<>();
		new CK().calculate(dir, result -> map.put(result.getClassName(), result));
		return map;
	}

	protected static Map<String, CKClassResult> run(String dir, Callable<List<ClassLevelMetric>> classLevelMetrics, Callable<List<MethodLevelMetric>> methodLevelMetrics) {
		Map<String, CKClassResult> map = new HashMap<>();
		new CK(classLevelMetrics, methodLevelMetrics).calculate(dir, result -> map.put(result.getClassName(), result));
		return map;
	}
}
