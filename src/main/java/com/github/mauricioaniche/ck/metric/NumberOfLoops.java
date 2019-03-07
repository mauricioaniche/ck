package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
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
	public void setResult(CKMethodResult result) {
		result.setLoopQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setLoopQty(qty);
	}
}
