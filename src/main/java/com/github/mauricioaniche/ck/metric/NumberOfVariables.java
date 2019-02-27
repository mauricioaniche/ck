package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.*;

public class NumberOfVariables extends ASTVisitor implements MethodLevelMetric {
	private int qty = 0;

	@Override
	public void setResult(MethodMetric result) {
		result.setVariablesQty(qty);
	}

	public boolean visit(VariableDeclarationFragment node) {
		qty++;
		return super.visit(node);
	}


}
