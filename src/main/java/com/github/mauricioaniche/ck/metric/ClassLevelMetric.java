package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.eclipse.jdt.core.dom.CompilationUnit;

public interface ClassLevelMetric {
	void setResult(CKClassResult result);
	
	default void setClassName(String className) {

	}
}
