package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public interface MethodLevelMetric {
	void setResult(CKMethodResult result);
	
	default void setMethodName(String methodName) {

	}
}
