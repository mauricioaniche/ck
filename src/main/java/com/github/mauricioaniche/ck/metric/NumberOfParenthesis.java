package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.TryStatement;

public class NumberOfParenthesis extends ASTVisitor implements Metric, MethodLevelMetric {

	private int qty = 0;

	public boolean visit(ParenthesizedExpression node) {
		qty++;
		return true;
	}

	@Override
	public void setResult(MethodMetric result) {
		result.setParenthesizedExpsQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setParenthesizedExpsQty(qty);
	}
}
