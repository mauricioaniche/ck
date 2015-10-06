package br.com.aniche.ck.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;

import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public interface Metric {

	void execute(CompilationUnit cu, CKReport report);
	void setResult(CKNumber result);
}
