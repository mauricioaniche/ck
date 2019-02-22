package com.github.mauricioaniche.ck;

import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.Callable;

import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelVisitor;
import com.github.mauricioaniche.ck.util.LOCCalculator;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

import com.github.mauricioaniche.ck.metric.ClassInfo;
import com.github.mauricioaniche.ck.metric.Metric;

public class MetricsExecutor extends FileASTRequestor {

	private CKReport report;
	private Callable<List<Metric>> metrics;
	private Callable<List<MethodLevelMetric>> methodLevelMetrics;

	private static Logger log = Logger.getLogger(MetricsExecutor.class);
	
	public MetricsExecutor(Callable<List<Metric>> metrics, Callable<List<MethodLevelMetric>> methodLevelMetrics) {
		this.metrics = metrics;
		this.methodLevelMetrics = methodLevelMetrics;
		this.report = new CKReport();
	}


	@Override
	public void acceptAST(String sourceFilePath, 
			CompilationUnit cu) {
		
		CKNumber result = null;
		
		try {
			ClassInfo info = new ClassInfo();
			cu.accept(info);
			if(info.getClassName()==null) return;
		
			result = new CKNumber(sourceFilePath, info.getClassName(), info.getType());
			
			int loc = new LOCCalculator().calculate(new FileInputStream(sourceFilePath));
			result.setLoc(loc);

			// calculate class level metrics
			for(Metric visitor : metrics.call()) {
				visitor.execute(cu, result);
				visitor.setResult(result);
			}

			// calculate metric level metrics
			MethodLevelVisitor methodVisitor = new MethodLevelVisitor(methodLevelMetrics);
			cu.accept(methodVisitor);
			result.setMethods(methodVisitor.getMap());

			log.info(result);
			report.add(result);
		} catch(Exception e) {
			if(result!=null) result.error();
			log.error("error in " + sourceFilePath, e);
		}
	}
	
	public CKReport getReport() {
		return report;
	}
	
}
