package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class RFC implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private final HashSet<String> methodInvocations = new HashSet<String>();

	public void visit(MethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		count(node.getName()  + "/" + arguments(node.arguments()), binding);
	}

	private void count(String methodName, IMethodBinding binding) {
		if(binding!=null) {
			String method = getMethodName(binding);
			methodInvocations.add(method);
		} else {
			methodInvocations.add(methodName);
		}
	}

	private String arguments(List<?> arguments) {
		if(arguments==null || arguments.isEmpty()) return "0";
		return "" + arguments.size();
	}

	private String getMethodName(IMethodBinding binding) {
		ITypeBinding[] args = binding.getParameterTypes();
		String argumentList = Stream.of(args).map(ITypeBinding::getName).reduce("", (s, s2) -> s + s2);;
		String method =
				binding.getDeclaringClass().getQualifiedName()
						+ "."
						+ binding.getName()
						+ "/"
						+ binding.getTypeArguments().length
						+ "["
						+ argumentList
						+ "]";
		return method;
	}

	public void visit(SuperMethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		count(node.getName()  + "/" + arguments(node.arguments()), binding);
	}

	public void visit(ExpressionMethodReference node) {
		IMethodBinding binding = node.resolveMethodBinding();
		count(node.getName().toString(), binding);
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
