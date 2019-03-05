package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.*;

public class NumberOfComparisons extends ASTVisitor implements Metric, MethodLevelMetric {

	private int qty = 0;

	@Override
	public boolean visit(InfixExpression node) {

		if(node.getOperator() == InfixExpression.Operator.EQUALS)
			qty++;

		return super.visit(node);

	}

	@Override
	public void setResult(MethodMetric result) {
		result.setComparisonsQty(qty);
	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setComparisonsQty(qty);
	}
}
