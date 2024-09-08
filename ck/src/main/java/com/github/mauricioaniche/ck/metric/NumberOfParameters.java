package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class NumberOfParameters implements CKASTVisitor, MethodLevelMetric{

	private int qty = 0;

	public void visit(MethodDeclaration node) {
		qty = node.parameters() == null ? 0 : node.parameters().size();
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setParametersQty(qty);
	}


}
