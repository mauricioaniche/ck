package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

public class NumberOfSubClassesLambdasAndAnonymousClasses implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private int anonymousClassesQty = 0;
	private int subClassesQty = 0;
	private int lambdasQty = 0;

	public void visit(TypeDeclaration node) {
		subClassesQty++;
	}

	public void visit(EnumDeclaration node) {
		// we count enum as class declaration!
		subClassesQty++;
	}

	public void visit(LambdaExpression node) {
		lambdasQty++;
	}

	public void visit(AnonymousClassDeclaration node) {
		anonymousClassesQty++;
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setAnonymousClassesQty(anonymousClassesQty);
		result.setSubClassesQty(subClassesQty - 1); // -1 as it counts its own class here
		result.setLambdasQty(lambdasQty);

	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setAnonymousClassesQty(anonymousClassesQty);
		result.setSubClassesQty(subClassesQty);
		result.setLambdasQty(lambdasQty);
	}
}
