package com.github.mauricioaniche.ck;

import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.Callable;

import com.github.mauricioaniche.ck.metric.*;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

import static com.github.mauricioaniche.ck.util.LOCCalculator.calculate;

public class MetricsExecutor extends FileASTRequestor {

	private Callable<List<ClassLevelMetric>> classLevelMetrics;
	private Callable<List<MethodLevelMetric>> methodLevelMetrics;
	private CKNotifier notifier;

	private static Logger log = Logger.getLogger(MetricsExecutor.class);
	
	public MetricsExecutor(Callable<List<ClassLevelMetric>> classLevelMetrics, Callable<List<MethodLevelMetric>> methodLevelMetrics, CKNotifier notifier) {
		this.classLevelMetrics = classLevelMetrics;
		this.methodLevelMetrics = methodLevelMetrics;
		this.notifier = notifier;
	}


	@Override
	public void acceptAST(String sourceFilePath, 
			CompilationUnit cu) {
		
		CKClassResult result = null;
		
		try {
			ClassInfo info = new ClassInfo();
			cu.accept(info);
			if(info.getClassName()==null) return;
		
			result = new CKClassResult(sourceFilePath, info.getClassName(), info.getType(), info.getModifiers());
			
			int loc = calculate(new FileInputStream(sourceFilePath));
			result.setLoc(loc);

			// calculate class level classLevelMetrics
			for(ClassLevelMetric visitor : classLevelMetrics.call()) {
				visitor.execute(cu, result);
				visitor.setResult(result);
			}

			// calculate metric level classLevelMetrics
			MethodLevelVisitor methodLevelVisitor = new MethodLevelVisitor(methodLevelMetrics, cu);
			ASTVisitor astVisitor = new IgnoreSubClasses(methodLevelVisitor);
			cu.accept(astVisitor);
			result.setMethods(methodLevelVisitor.getMap());

			log.info(result);
			notifier.notify(result);
		} catch(Exception e) {
			if(result!=null) {
				result.error(e);
				notifier.notify(result);
			}
			log.error("error in " + sourceFilePath, e);
		}
	}
	
}
