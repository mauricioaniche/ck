package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;

public class NOSI implements CKASTVisitor, ClassLevelMetric {

	private int count = 0;

	public void visit(MethodInvocation node) {

		IMethodBinding binding = node.resolveMethodBinding();
		if(binding!=null && Modifier.isStatic(binding.getModifiers()))
				count++;
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setNosi(count);
	}

}
