package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MethodLevelFieldUsageCount extends ASTVisitor implements MethodLevelMetric {
	private Set<String> declaredFields;
	private Map<String, Integer> occurrences;

	public MethodLevelFieldUsageCount() {
		declaredFields = new HashSet<>();
		this.occurrences = new HashMap<>();
	}

	public boolean visit(MethodDeclaration node) {

		IMethodBinding binding = node.resolveBinding();
		IVariableBinding[] fields = binding.getDeclaringClass().getDeclaredFields();

		for (IVariableBinding field : fields) {
			declaredFields.add(field.getName().toString());
		}
		return super.visit(node);
	}

	public boolean visit(SimpleName node) {

		if(declaredFields.contains(node.toString())) {
			String var = node.getIdentifier();
			if (!occurrences.containsKey(var))
				occurrences.put(var, -1);

			occurrences.put(var, occurrences.get(var) + 1);
		}

		return super.visit(node);
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setFieldUsage(occurrences);
	}
}
