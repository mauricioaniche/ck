package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class NOPF extends ASTVisitor implements ClassLevelMetric {

	private int fields;

	@Override
	public boolean visit(FieldDeclaration node) {
		if(Modifier.isPublic(node.getModifiers())) 
			fields++;

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNopf(fields);
	}
}
