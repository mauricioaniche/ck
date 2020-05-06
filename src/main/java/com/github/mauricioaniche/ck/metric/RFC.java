package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.JDTUtils;
import org.eclipse.jdt.core.dom.*;
import java.util.HashSet;

public class RFC implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {
	private HashSet<String> methodInvocations = new HashSet<String>();

	public void visit(MethodInvocation node) {
		String methodName = JDTUtils.getQualifiedMethodFullName(node);
		methodInvocations.add(methodName);
	}

	public void visit(SuperMethodInvocation node) {
		String methodName = JDTUtils.getQualifiedMethodFullName(node);
		methodInvocations.add(methodName);
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setRfc(methodInvocations.size());
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setRfc(methodInvocations.size());
		result.setMethodInvocations(methodInvocations);
	}
}