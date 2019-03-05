package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.*;

public class NumberOfStrings extends ASTVisitor implements Metric, MethodLevelMetric {

	private int qty = 0;

	public boolean visit(StringLiteral node) {
		qty++;
		return super.visit(node);
	}
	@Override
	public void setResult(MethodMetric result) {
		result.setStringsQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setStringsQty(qty);
	}
}
