package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class NumberOfAssignments extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	@Override
	public boolean visit(Assignment node) {
		qty++;
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		if(node.getInitializer()!=null)
			qty++;

		return super.visit(node);
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setAssignmentsQty(qty);

	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setAssignmentsQty(qty);
	}
}
