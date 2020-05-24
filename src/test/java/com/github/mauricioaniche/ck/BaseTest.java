package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public abstract class BaseTest {

	protected Map<String, CKClassResult> report;

	protected static String fixturesDir() {
		try {
			String cfgFile = new File(BaseTest.class.getResource("/").getPath() + "../../fixtures/").getCanonicalPath();
			return cfgFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@AfterAll
	public void checkNulls() {
		report = run(fixturesDir() + "/cbo");
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
