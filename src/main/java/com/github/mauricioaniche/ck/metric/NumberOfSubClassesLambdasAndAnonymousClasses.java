package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

public class NumberOfSubClassesLambdasAndAnonymousClasses extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int anonymousClassesQty = 0;
	private int subClassesQty = 0;
	private int lambdasQty = 0;

	public boolean visit(TypeDeclaration node) {
		subClassesQty++;
		return super.visit(node);
	}

	public boolean visit(LambdaExpression node) {
		lambdasQty++;
		return super.visit(node);
	}

	public boolean visit(AnonymousClassDeclaration node) {
		anonymousClassesQty++;
		return super.visit(node);
	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult result) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setAnonymousClassesQty(anonymousClassesQty);
		result.setSubClassesQty(subClassesQty - 1);
		result.setLambdasQty(lambdasQty);

	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setAnonymousClassesQty(anonymousClassesQty);
		result.setSubClassesQty(subClassesQty);
		result.setLambdasQty(lambdasQty);
	}
}
