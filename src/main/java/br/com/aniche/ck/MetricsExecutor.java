package br.com.aniche.ck;

import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

import br.com.aniche.ck.metric.ClassInfo;
import br.com.aniche.ck.metric.Metric;

public class MetricsExecutor extends FileASTRequestor {

	private CKReport report;
	private Callable<List<Metric>> metrics;
	
	public MetricsExecutor(Callable<List<Metric>> metrics) {
		this.metrics = metrics;
		this.report = new CKReport();
	}


	@Override
	public void acceptAST(String sourceFilePath, 
			CompilationUnit cu) {
		
		try {
			ClassInfo info = new ClassInfo();
			cu.accept(info);
			if(info.getClassName()==null) return;
		
			CKNumber result = new CKNumber(sourceFilePath, info.getClassName());
			for(Metric visitor : metrics.call()) {
				visitor.execute(cu, report);
				visitor.setResult(result);
			}
			report.add(result);
		} catch(Exception e) {
			// just ignore... sorry!
			// later on: log
			System.err.println("error in " + sourceFilePath);
			e.printStackTrace(System.err);
		}
	}
	
	public CKReport getReport() {
		return report;
	}
	
}
