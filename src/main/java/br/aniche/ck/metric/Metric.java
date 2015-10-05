package br.aniche.ck.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;

import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;

public interface Metric {

	void execute(CompilationUnit cu, CKReport report);
	void setResult(CKNumber result);
}
