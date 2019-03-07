package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKNumber;
import org.eclipse.jdt.core.dom.*;

public class NOSI extends ASTVisitor implements ClassLevelMetric {

	private int count = 0;

	public boolean visit(MethodInvocation node) {

		IMethodBinding binding = node.resolveMethodBinding();
		if(binding!=null && Modifier.isStatic(binding.getModifiers()))
				count++;
		
		return super.visit(node);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNosi(count);
	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number) {
		cu.accept(new IgnoreSubClasses(this));
	}
	
}
