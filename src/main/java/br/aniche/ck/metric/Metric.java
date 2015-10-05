package br.aniche.ck.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;

import br.aniche.ck.CKNumber;

public interface Metric {

	void execute(CompilationUnit cu);
	void setResult(CKNumber result);
}
