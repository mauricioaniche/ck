package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public interface Metric {

	void execute(CompilationUnit cu, CKNumber result, CKReport report);
	void setResult(CKNumber result);
}
