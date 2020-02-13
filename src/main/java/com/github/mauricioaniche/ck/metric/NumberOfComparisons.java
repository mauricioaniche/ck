package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.InfixExpression;

public class NumberOfComparisons implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	@Override
	public void visit(InfixExpression node) {

		if(node.getOperator() == InfixExpression.Operator.EQUALS)
			qty++;

		if(node.getOperator() == InfixExpression.Operator.NOT_EQUALS)
			qty++;

	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setComparisonsQty(qty);
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setComparisonsQty(qty);
	}
}
