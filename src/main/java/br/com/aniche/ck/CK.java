package br.com.aniche.ck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import br.com.aniche.ck.metric.CBO;
import br.com.aniche.ck.metric.DIT;
import br.com.aniche.ck.metric.LCOM;
import br.com.aniche.ck.metric.Metric;
import br.com.aniche.ck.metric.NOC;
import br.com.aniche.ck.metric.NOM;
import br.com.aniche.ck.metric.RFC;
import br.com.aniche.ck.metric.WMC;

public class CK {

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
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		
		Map<?, ?> options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
		parser.setCompilerOptions(options);

		String[] srcDirs = FileUtils.getAllDirs(path);
		String[] javaFiles = FileUtils.getAllJavaFiles(path);
		log.info("Found " + javaFiles.length + " java files");

		parser.setEnvironment(null, srcDirs, null, true);
		
		MetricsExecutor storage = new MetricsExecutor(() -> metrics());
		parser.createASTs(javaFiles, null, new String[0], storage, null);
		return storage.getReport();
	}


	private List<Metric> metrics() {
		List<Metric> all = defaultMetrics();
		all.addAll(userMetrics());
		
		return all;
	}

	private List<Metric> defaultMetrics() {
		return new ArrayList<>(Arrays.asList(new DIT(), new NOC(), new WMC(), new CBO(), new LCOM(), new RFC(), new NOM()));
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
