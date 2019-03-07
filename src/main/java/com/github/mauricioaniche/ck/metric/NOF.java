package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

public class NOF extends ASTVisitor implements ClassLevelMetric {

	private int fields;

	@Override
	public boolean visit(FieldDeclaration node) {
		fields++;

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNof(fields);
	}
}
