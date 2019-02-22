package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public interface MethodLevelMetric {
	boolean visit(MethodDeclaration node);
	void setResult(MethodMetric result);
}
