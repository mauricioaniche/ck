package com.github.mauricioaniche.ck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jgit.lib.ObjectId;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.util.File;
import com.github.mauricioaniche.ck.util.FileUtils;
import com.github.mauricioaniche.ck.util.GitFactory;
import com.github.mauricioaniche.ck.util.MetricsFinder;
import com.google.common.collect.Lists;

public class CK {

	private static final int MAX_AT_ONCE;
	
	static {
		String jdtMax = System.getProperty("jdt.max");
		if(jdtMax!=null) {
			MAX_AT_ONCE = Integer.parseInt(jdtMax);
		} else {
			long maxMemory= Runtime.getRuntime().maxMemory() / (1 << 20); // in MiB
			
			if      (maxMemory >= 2000) MAX_AT_ONCE= 400;
			else if (maxMemory >= 1500) MAX_AT_ONCE= 300;
			else if (maxMemory >= 1000) MAX_AT_ONCE= 200;
			else if (maxMemory >=  500) MAX_AT_ONCE= 100;
			else                        MAX_AT_ONCE=  25;
		}
	}


	private static Logger log = Logger.getLogger(CK.class);

	Callable<List<ClassLevelMetric>> classLevelMetrics;
	Callable<List<MethodLevelMetric>> methodLevelMetrics;

	public CK(Callable<List<ClassLevelMetric>> classLevelMetrics, Callable<List<MethodLevelMetric>> methodLevelMetrics) {
		this.classLevelMetrics = classLevelMetrics;
		this.methodLevelMetrics = methodLevelMetrics;
	}

	public CK() {
		MetricsFinder finder = new MetricsFinder();

		this.classLevelMetrics = () -> finder.allClassLevelMetrics();
		this.methodLevelMetrics = () -> finder.allMethodLevelMetrics();
	}

	public void calculate(String path, CKNotifier notifier) {
		
		//It obtains all folders and files in current repository
		String[] srcDirs = FileUtils.getAllDirs(path);
		String[] javaFiles = FileUtils.getAllJavaFiles(path);
		log.info("Found " + javaFiles.length + " java files");
		
		//It inits the metrics executor
		MetricsExecutor storage = new MetricsExecutor(classLevelMetrics, methodLevelMetrics, notifier);
		
		//It divides the files in groups, maybe for some optimization
		List<List<String>> partitions = Lists.partition(Arrays.asList(javaFiles), MAX_AT_ONCE);
		log.debug("Max partition size: " + MAX_AT_ONCE + ", total partitions=" + partitions.size());

		for(List<String> partition : partitions) {
			log.debug("Next partition");
			ASTParser parser = ASTParser.newParser(AST.JLS11);
			
			parser.setResolveBindings(true);
			parser.setBindingsRecovery(true);
			
			Map<String, String> options = JavaCore.getOptions();
			JavaCore.setComplianceOptions(JavaCore.VERSION_11, options);
			parser.setCompilerOptions(options);
			parser.setEnvironment(null, srcDirs, null, true);
			parser.createASTs(partition.toArray(new String[partition.size()]), null, new String[0], storage, null);
		}
		
		log.info("Finished parsing");
    }
	
	public void calculate(ObjectId commitId, CKNotifier notifier) {
		
		var factory = new GitFactory();
		var folder = factory.folder();
		
		//It inits the metrics executor
		MetricsExecutor storage = new MetricsExecutor(
			classLevelMetrics, 
			methodLevelMetrics, 
			notifier
		);
		
		var list = new ArrayList<File>();
		try {
			factory
			.treeStart(commitId, walk -> {
				var file = new File(
					walk.getPathString(), 
					walk.getObjectId(0)
				);
				
				if(file.name().endsWith(".java")) 
					list.add(file);
								
			});
		} catch (IOException e) {
			
			System.err.println(e.getMessage());
		}

		
		for(var file: list) {
			ASTParser parser = ASTParser.newParser(AST.JLS11);
			parser.setSource(file.get().toCharArray());
		
			Map<String, String> options = JavaCore.getOptions();
			parser.setCompilerOptions(options);
			parser.setUnitName("/" + folder + "/" + file.name());
			parser.setEnvironment(
				new String[] {factory.absoluteFolder()}, 
				new String[] {""}, 
				new String[] {"UTF-8"}, 
				true
			);
			
			parser.setResolveBindings(true);
			parser.setBindingsRecovery(true);
			
			JavaCore.setComplianceOptions(JavaCore.VERSION_11, options);
			var unit = (CompilationUnit) parser.createAST(null);
			
			storage.acceptAST(factory.absoluteFolder() + file.name(), unit);
		}
		
	}

}
