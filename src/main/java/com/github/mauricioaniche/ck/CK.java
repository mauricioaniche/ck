package com.github.mauricioaniche.ck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import com.github.mauricioaniche.ck.metric.CBO;
import com.github.mauricioaniche.ck.metric.DIT;
import com.github.mauricioaniche.ck.metric.LCOM;
import com.github.mauricioaniche.ck.metric.Metric;
import com.github.mauricioaniche.ck.metric.NOC;
import com.github.mauricioaniche.ck.metric.NOF;
import com.github.mauricioaniche.ck.metric.NOM;
import com.github.mauricioaniche.ck.metric.NOPF;
import com.github.mauricioaniche.ck.metric.NOPM;
import com.github.mauricioaniche.ck.metric.NOSF;
import com.github.mauricioaniche.ck.metric.NOSI;
import com.github.mauricioaniche.ck.metric.NOSM;
import com.github.mauricioaniche.ck.metric.RFC;
import com.github.mauricioaniche.ck.metric.WMC;
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

	public List<Callable<Metric>> pluggedMetrics; 
	private static Logger log = Logger.getLogger(CK.class);

	public CK() {
		this.pluggedMetrics = new ArrayList<>();
	}
	
	public CK plug(Callable<Metric> metric) {
		this.pluggedMetrics.add(metric);
		return this;
	}
	
	public CKReport calculate(String path) {
		String[] srcDirs = FileUtils.getAllDirs(path);
		String[] javaFiles = FileUtils.getAllJavaFiles(path);
		log.info("Found " + javaFiles.length + " java files");
		
		MetricsExecutor storage = new MetricsExecutor(() -> metrics());
		
		List<List<String>> partitions = Lists.partition(Arrays.asList(javaFiles), MAX_AT_ONCE);
		log.info("Max partition size: " + MAX_AT_ONCE + ", total partitions=" + partitions.size());

		for(List<String> partition : partitions) {
			log.info("Next partition");
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			
			parser.setResolveBindings(true);
			parser.setBindingsRecovery(true);
			
			Map<?, ?> options = JavaCore.getOptions();
			JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
			parser.setCompilerOptions(options);
			parser.setEnvironment(null, srcDirs, null, true);
			parser.createASTs(partition.toArray(new String[partition.size()]), null, new String[0], storage, null);
		}
		
		log.info("Finished parsing");
		return storage.getReport();
	}
	
	private List<Metric> metrics() {
		List<Metric> all = defaultMetrics();
		all.addAll(userMetrics());
		
		return all;
	}

	private List<Metric> defaultMetrics() {
		return new ArrayList<>(Arrays.asList(new DIT(), new NOC(), new WMC(), new CBO(), new LCOM(), new RFC(), new NOM(),
				new NOF(), new NOPF(), new NOSF(),
				new NOPM(), new NOSM(), new NOSI()));
	}

	private List<Metric> userMetrics() {
		try {
			List<Metric> userMetrics = new ArrayList<Metric>();
			
			for(Callable<Metric> metricToBeCreated : pluggedMetrics) {
				userMetrics.add(metricToBeCreated.call());
			}

			return userMetrics;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
