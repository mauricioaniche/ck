package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.MethodMetric;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParameterUsageCount extends ASTVisitor implements MethodLevelMetric {
	private Set<String> declaredVariables;
	private Map<String, Integer> occurences;

	public ParameterUsageCount() {
		declaredVariables = new HashSet<>();
		this.occurences = new HashMap<>();
	}

	@Override
	public void setResult(MethodMetric result) {
		result.setVariablesUsage(occurences);

	}

	public boolean visit(VariableDeclarationFragment node) {
		declaredVariables.add(node.getName().toString());
		return super.visit(node);

	}

	public boolean visit(SingleVariableDeclaration node) {
		declaredVariables.add(node.getName().toString());
		return super.visit(node);
	}

	public boolean visit(SimpleName node) {
		if(declaredVariables.contains(node.toString())) {
			String var = node.getIdentifier();
			if (!occurences.containsKey(var))
				occurences.put(var, 0);

			occurences.put(var, occurences.get(var) + 1);
		}

		return super.visit(node);
	}

}
