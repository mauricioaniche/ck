package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class NumberOfParameters extends ASTVisitor implements MethodLevelMetric{

	private int qty = 0;

	public boolean visit(MethodDeclaration node) {
		qty = node.parameters() == null ? 0 : node.parameters().size();

		return super.visit(node);
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setParametersQty(qty);
	}


}
