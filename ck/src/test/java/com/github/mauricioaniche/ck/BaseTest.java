package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;

import static com.github.mauricioaniche.ck.AssertResult.assertResultNotNull;

public abstract class BaseTest {

	protected Map<String, CKClassResult> report;

	protected static String fixturesDir() {
		try {
			String cfgFile = new File(BaseTest.class.getResource("/").getPath() + "../../fixtures/").getCanonicalPath();
			return URLDecoder.decode(cfgFile, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void checkNulls() {
		if(report==null)
			return;

		for(String reportedClass : report.keySet()) {
			// no nulls in getters of the class
			CKClassResult ckClassResult = report.get(reportedClass);
			assertResultNotNull(ckClassResult);
		}
	}

	protected static Map<String, CKClassResult> run(String dir) {
		Map<String, CKClassResult> map = new HashMap<>();
		new CK().calculate(dir, new CKNotifier() {
			@Override
			public void notify(CKClassResult result) {
				map.put(result.getClassName(), result);
			}

			@Override
			public void notifyError(String sourceFilePath, Exception e) {
				Assertions.fail(sourceFilePath, e);
			}
		});
		return map;
	}

	protected static Map<String, CKClassResult> runDebug(String dir) {
		return runDebug(dir, () -> Arrays.asList(new ASTDebugger()), () -> Collections.emptyList());
	}

	protected static Map<String, CKClassResult> runDebug(String dir, Callable<List<ClassLevelMetric>> classLevelMetrics, Callable<List<MethodLevelMetric>> methodLevelMetrics) {
		Map<String, CKClassResult> map = new HashMap<>();
		new CK(classLevelMetrics, methodLevelMetrics).calculate(dir, new CKNotifier() {
			@Override
			public void notify(CKClassResult result) {
				map.put(result.getClassName(), result);
			}

			@Override
			public void notifyError(String sourceFilePath, Exception e) {
				Assertions.fail(sourceFilePath, e);
			}
		});
		return map;
	}
}
