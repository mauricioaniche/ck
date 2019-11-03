package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class NumberOfVariables extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {
	private int qty = 0;

	@Override
	public void setResult(CKMethodResult result) {
		result.setVariablesQty(qty);
	}

	public boolean visit(VariableDeclarationFragment node) {
		qty++;
		return super.visit(node);
	}

	@Override
	public void setResult(CKClassResult result) {

		result.setVariablesQty(qty);

	}
}
