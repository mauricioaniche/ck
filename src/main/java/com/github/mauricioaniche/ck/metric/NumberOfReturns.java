package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ReturnStatement;

public class NumberOfReturns extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {
	private int qty = 0;

	@Override
	public void setResult(MethodMetric result) {
		result.setReturnQty(qty);

	}

	public boolean visit(ReturnStatement node) {
		qty++;
		return super.visit(node);
	}

	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKNumber result) {
		result.setReturnQty(qty);

	}
}
