package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.InfixExpression;

import java.util.Arrays;

public class NumberOfMathOperators implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	static InfixExpression.Operator[] operators = new InfixExpression.Operator[] {
		InfixExpression.Operator.TIMES, InfixExpression.Operator.DIVIDE,
			InfixExpression.Operator.REMAINDER,InfixExpression.Operator.PLUS,
			InfixExpression.Operator.MINUS,InfixExpression.Operator.LEFT_SHIFT,
			InfixExpression.Operator.RIGHT_SHIFT_SIGNED,InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED
	};

	@Override
	public void visit(InfixExpression node) {

		if( Arrays.stream(operators).anyMatch(node.getOperator()::equals))
			qty++;

	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setMathOperationsQty(qty);
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setMathOperationsQty(qty);
	}
}
