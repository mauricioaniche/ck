package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class NumberOfAssignments implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	@Override
	public void visit(Assignment node) {
		qty++;
	}

	@Override
	public void visit(VariableDeclarationFragment node) {
		if(node.getInitializer()!=null)
			qty++;
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setAssignmentsQty(qty);

	}

	@Override
	public void setResult(CKClassResult result) {
		result.setAssignmentsQty(qty);
	}
}
