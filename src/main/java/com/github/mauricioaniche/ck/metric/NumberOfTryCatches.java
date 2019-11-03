package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TryStatement;

public class NumberOfTryCatches extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	public boolean visit(TryStatement node) {
		qty++;
		return true;
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setTryCatchQty(qty);

	}

	@Override
	public void setResult(CKClassResult result) {
		result.setTryCatchQty(qty);
	}
}
