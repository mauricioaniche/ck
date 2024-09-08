package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;

public class NumberOfParenthesis implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	public void visit(ParenthesizedExpression node) {
		qty++;
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setParenthesizedExpsQty(qty);

	}

	@Override
	public void setResult(CKClassResult result) {
		result.setParenthesizedExpsQty(qty);
	}
}
