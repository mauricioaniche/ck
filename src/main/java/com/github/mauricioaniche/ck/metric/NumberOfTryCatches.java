package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.*;

public class NumberOfTryCatches extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	public boolean visit(TryStatement node) {
		qty++;
		return true;
	}

	@Override
	public void setResult(MethodMetric result) {
		result.setTryCatchQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKNumber result) {
		result.setTryCatchQty(qty);
	}
}
