package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.*;
import java.util.concurrent.Callable;

public class MethodLevelVisitor extends ASTVisitor {

	private Map<String, MethodMetric> methods;
	private Callable<List<MethodLevelMetric>> metrics;

	public MethodLevelVisitor(Callable<List<MethodLevelMetric>> metrics) {
		this.metrics = metrics;
		methods = new HashMap<>();
	}

	public boolean visit(MethodDeclaration node) {
		String methodName = node.getName().getFullyQualifiedName();

		MethodMetric number = new MethodMetric(methodName);
		methods.put(methodName, number);

		try {
			for(MethodLevelMetric metric : metrics.call()) {
				metric.visit(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.visit(node);
	}

	public Map<String, MethodMetric> getMap() {
		return Collections.unmodifiableMap(methods);
	}
}
