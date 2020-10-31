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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		String[] javaFiles = FileUtils.getAllJavaFiles(path);
		log.info("Found " + javaFiles.length + " java files");

		calculate(Paths.get(path), notifier,
		 	Stream.of(javaFiles)
				.map(Paths::get)
				.toArray(Path[]::new)
			);
	}

	/**
	 * Convenience method to call ck with a path rather than a string
	 * @param path The path which contain the java class files to analyse
	 * @param notifier Handle to process the results and handle errors
	 */
	public void calculate(Path path, CKNotifier notifier) {
		calculate(path.toString(), notifier);
	}

	/**
	 * Calculate metrics for the passed javaFilePaths. Uses path to set the environment
	 * @param path The environment to where the source code is located
	 * @param notifier Handle to process the results and handle errors
	 * @param javaFilePaths The files to collect metrics of.
	 */
	public void calculate(Path path, CKNotifier notifier, Path... javaFilePaths) {
		String[] srcDirs = FileUtils.getAllDirs(path.toString());
		log.info("Found " + srcDirs.length + " src dirs");

		String[] allDependencies = useJars ? FileUtils.getAllJars(path.toString()) : null;

		if(useJars)
			log.info("Found " + allDependencies.length + " jar dependencies");
		
		MetricsExecutor storage = new MetricsExecutor(classLevelMetrics, methodLevelMetrics, notifier);

		// Converts the paths to strings and makes the method support relative paths as well.
		List<String> strJavaFilePaths = Stream.of(javaFilePaths).map(file -> file.isAbsolute() ? file.toString() : path.resolve(file).toString()).collect(Collectors.toList());

		List<List<String>> partitions = Lists.partition(strJavaFilePaths, maxAtOnce);
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
