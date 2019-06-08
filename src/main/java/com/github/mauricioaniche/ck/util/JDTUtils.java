package com.github.mauricioaniche.ck.util;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import java.util.ArrayList;
import java.util.List;

public class JDTUtils {

	public static String getMethodFullName(MethodDeclaration node) {
		String methodName = node.getName().getFullyQualifiedName();

		int parameterCount = node.parameters()==null ? 0 : node.parameters().size();
		List<String> parameterTypes = new ArrayList<>();

		if(parameterCount > 0) {
			for(Object p0 : node.parameters()) {
				SingleVariableDeclaration parameter = (SingleVariableDeclaration) p0;

				ITypeBinding binding = parameter.getType().resolveBinding();

				String v;
				if(binding == null || binding.isRecovered())
					v = parameter.getType().toString();
				else
					v = binding.getQualifiedName();

				if(parameter.isVarargs()) v+="[]";

				parameterTypes.add(v);
			}
		}

		return String.format("%s/%d%s%s%s",
				methodName,
				parameterCount,
				(parameterCount > 0 ? "[" : ""),
				(parameterCount > 0 ? String.join(",", parameterTypes) : ""),
				(parameterCount > 0 ? "]" : "")
		);
	}

	/**
	 * If the method has a body, we can get the starting line of the method, ignoring any possible
	 * Javadoc at the top of it.
	 * If there's no body, JDT doesn't create a 'body', and thus, we can't get its starting position; thus,
	 * we fall back to the starting position of the methoddeclarationnode, which can contain the javadoc.
	 * This seems like an exceptional case, though.
	 * TODO: better ideas are welcome.
	 */

	public static int getStartLine(CompilationUnit cu, MethodDeclaration node) {
		return node.getBody() != null ?
				cu.getLineNumber(node.getBody().getStartPosition()) :
				cu.getLineNumber(node.getStartPosition());
	}
}
