package br.aniche.ck.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;

import br.aniche.ck.CalculatedCK;

public interface Metric {

	void execute(CompilationUnit cu, CalculatedCK result);
}
