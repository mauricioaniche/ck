package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.*;

public class NumberOfMaxNestedBlock extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int current = 0;
	private int max = 0;

	@Override
	public boolean visit(Block node) {
		current++;
		max = Math.max(current, max);

		return super.visit(node);
	}

	public void endVisit(Block node) {
		current--;
	}

	@Override
	public void setResult(MethodMetric result) {
		result.setMaxNestedBlocks(max);

	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKNumber result) {
		result.setMaxNestedBlocks(max);
	}
}
