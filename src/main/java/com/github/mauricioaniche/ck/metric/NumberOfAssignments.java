package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class NumberOfAssignments extends ASTVisitor implements Metric, MethodLevelMetric {

	private int qty = 0;

	@Override
	public boolean visit(Assignment node) {
		qty++;
		return super.visit(node);
	}

	@Override
	public void setResult(MethodMetric result) {
		result.setAssignmentsQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setAssignmentsQty(qty);
	}
}
