package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.*;

public class NumberOfLoops extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	public boolean visit(EnhancedForStatement node) {
		qty++;
		return super.visit(node);
	}

	public boolean visit(DoStatement node) {
		qty++;
		return super.visit(node);
	}

	public boolean visit(WhileStatement node) {
		qty++;
		return super.visit(node);
	}

	public boolean visit(ForStatement node) {
		qty++;
		return super.visit(node);
	}

	@Override
	public void setResult(MethodMetric result) {
		result.setLoopQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKNumber result) {
		result.setLoopQty(qty);
	}
}
