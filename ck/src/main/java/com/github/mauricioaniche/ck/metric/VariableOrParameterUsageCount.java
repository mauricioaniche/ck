package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VariableOrParameterUsageCount implements CKASTVisitor, MethodLevelMetric, VariableOrFieldMetric {
	private Set<String> declaredVariables;
	private Map<String, Integer> occurrences;

	public VariableOrParameterUsageCount() {
		declaredVariables = new HashSet<>();
		this.occurrences = new HashMap<>();
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setVariablesUsage(occurrences);
	}

	public void visit(VariableDeclarationFragment node) {
		declaredVariables.add(node.getName().toString());

	}

	public void visit(SingleVariableDeclaration node) {
		declaredVariables.add(node.getName().toString());
	}

	public void visit(SimpleName node) {
		if(declaredVariables.contains(node.toString())) {
			String var = node.getIdentifier();
			if (!occurrences.containsKey(var))
				occurrences.put(var, -1);

			occurrences.put(var, occurrences.get(var) + 1);
		}
	}

}
