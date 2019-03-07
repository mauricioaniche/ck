package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
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

	@Override
	public void endVisit(Block node) {
		current--;
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setMaxNestedBlocks(max - 1); // -1 because the method block is considered a block.

	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setMaxNestedBlocks(max - 1);
	}
}
