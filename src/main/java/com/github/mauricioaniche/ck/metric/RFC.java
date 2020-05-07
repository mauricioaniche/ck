package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.JDTUtils;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.Set;

public class RFC implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private final Set<String> methodInvocations = new HashSet<String>();

	public void visit(MethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		String methodName = binding != null ? JDTUtils.getMethodFullName(binding) : JDTUtils.getMethodFullName(node);
		count(methodName);
	}

	private void count(String methodName) {
		methodInvocations.add(methodName);
	}

	public void visit(SuperMethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		String methodName = JDTUtils.getMethodFullName(binding);
		count(methodName);
	}

	public void visit(ExpressionMethodReference node) {
		IMethodBinding binding = node.resolveMethodBinding();
		String methodName = binding != null ? JDTUtils.getMethodFullName(binding) : JDTUtils.getMethodFullName(node);
		count(methodName);
	}
	
	@Override
	public void setResult(CKClassResult result) {
		result.setRfc(methodInvocations.size());
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setRfc(methodInvocations.size());
	}
}
