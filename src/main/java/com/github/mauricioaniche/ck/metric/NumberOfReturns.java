package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ReturnStatement;

public class NumberOfReturns extends ASTVisitor implements MethodLevelMetric {
	private int qty = 0;

	@Override
	public void setResult(MethodMetric result) {
		result.setReturnQty(qty);

	}

	public boolean visit(ReturnStatement node) {
		qty++;
		return super.visit(node);
	}

}
