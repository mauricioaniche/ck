package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.List;

public class RFC implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private HashSet<String> methodInvocations = new HashSet<String>();

	public void visit(MethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		count(node.getName()  + "/" + arguments(node.arguments()), binding);
	}

	private String arguments(List<?> arguments) {
		if(arguments==null || arguments.isEmpty()) return "0";
		return "" + arguments.size();
	}

	private void count(String methodName, IMethodBinding binding) {
		if(binding!=null) {
			String method = getMethodName(binding);
			methodInvocations.add(method);
		} else {
			methodInvocations.add(methodName);
		}
	}
	
	public void visit(SuperMethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		count(node.getName()  + "/" + arguments(node.arguments()), binding);
	}

	private String getMethodName(IMethodBinding binding) {
		
		String argumentList = "";
		ITypeBinding[] args = binding.getParameterTypes();
		for(ITypeBinding arg : args) {
			argumentList += arg.getName();
		}
		String method = binding.getDeclaringClass().getQualifiedName() + "." + binding.getName() + "/" + binding.getTypeArguments().length + "[" + argumentList + "]";
		return method;
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
