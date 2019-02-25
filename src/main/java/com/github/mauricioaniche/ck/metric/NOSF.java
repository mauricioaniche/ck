package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class NOSF extends ASTVisitor implements Metric {

	private int fields;

	@Override
	public boolean visit(FieldDeclaration node) {
		if(Modifier.isStatic(node.getModifiers())) 
			fields++;

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNosf(fields);
	}
}
