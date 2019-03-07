package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class NOPM extends ASTVisitor implements ClassLevelMetric {

	private int methods;

	@Override
	public boolean visit(MethodDeclaration node) {
		if(Modifier.isPublic(node.getModifiers())) 
			methods++;

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNopm(methods);
	}
}
