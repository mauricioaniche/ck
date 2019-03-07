package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

public class NumberOfStringLiterals extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	public boolean visit(StringLiteral node) {
		qty++;
		return super.visit(node);
	}
	@Override
	public void setResult(CKMethodResult result) {
		result.setStringLiteralsQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setStringLiteralsQty(qty);
	}
}
