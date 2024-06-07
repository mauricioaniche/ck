package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;

public interface ClassLevelMetric {
	default boolean isVerbose() {
		return false;
	}
	void setResult(CKClassResult result);
	
	default void setClassName(String className) {

	}
}
