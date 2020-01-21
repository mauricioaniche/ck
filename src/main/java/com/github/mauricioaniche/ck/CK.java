package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.util.FileUtils;
import com.github.mauricioaniche.ck.util.MetricsFinder;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CK {

	private final int maxAtOnce;
	private final boolean useJars;
	
	private static Logger log = Logger.getLogger(CK.class);

	Callable<List<ClassLevelMetric>> classLevelMetrics;
	Callable<List<MethodLevelMetric>> methodLevelMetrics;

	// mostly for testing purposes
	public CK(Callable<List<ClassLevelMetric>> classLevelMetrics, Callable<List<MethodLevelMetric>> methodLevelMetrics) {
		this.useJars = false;
		this.classLevelMetrics = classLevelMetrics;
		this.methodLevelMetrics = methodLevelMetrics;
		this.maxAtOnce = 100;
	}


	public CK(boolean useJars, int maxAtOnce, boolean variablesAndFields) {
		MetricsFinder finder = new MetricsFinder();
		this.classLevelMetrics = () -> finder.allClassLevelMetrics();
		this.methodLevelMetrics = () -> finder.allMethodLevelMetrics(variablesAndFields);

		this.useJars = useJars;
		if(maxAtOnce == 0)
			this.maxAtOnce = getMaxPartitionBasedOnMemory();
		else
			this.maxAtOnce = maxAtOnce;
	}

	public CK() {
		this(false, 0, true);
	}

	public void calculate(String path, CKNotifier notifier) {
		String[] srcDirs = FileUtils.getAllDirs(path);
		String[] javaFiles = FileUtils.getAllJavaFiles(path);
		String[] allDependencies = useJars ? FileUtils.getAllJars(path) : null;

		log.info("Found " + javaFiles.length + " java files");
		if(useJars)
			log.info("Found " + allDependencies.length + " jar dependencies");
		
		MetricsExecutor storage = new MetricsExecutor(classLevelMetrics, methodLevelMetrics, notifier);
		
		List<List<String>> partitions = Lists.partition(Arrays.asList(javaFiles), maxAtOnce);
		log.debug("Max partition size: " + maxAtOnce + ", total partitions=" + partitions.size());

		for(List<String> partition : partitions) {
			log.debug("Next partition");
			ASTParser parser = ASTParser.newParser(AST.JLS11);
			
			parser.setResolveBindings(true);
			parser.setBindingsRecovery(true);
			
			Map<String, String> options = JavaCore.getOptions();
			JavaCore.setComplianceOptions(JavaCore.VERSION_11, options);
			parser.setCompilerOptions(options);
			parser.setEnvironment(allDependencies, srcDirs, null, true);
			parser.createASTs(partition.toArray(new String[partition.size()]), null, new String[0], storage, null);
		}
		
		log.info("Finished parsing");
    }

	private int getMaxPartitionBasedOnMemory() {
		long maxMemory= Runtime.getRuntime().maxMemory() / (1 << 20); // in MiB

		if      (maxMemory >= 2000) return 400;
		else if (maxMemory >= 1500) return 300;
		else if (maxMemory >= 1000) return 200;
		else if (maxMemory >=  500) return 100;
		else                        return 25;
	}


}
