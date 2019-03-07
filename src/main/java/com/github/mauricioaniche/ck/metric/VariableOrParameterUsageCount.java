package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VariableOrParameterUsageCount extends ASTVisitor implements MethodLevelMetric {
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
			if (!occurrences.containsKey(var))
				occurrences.put(var, -1);

			occurrences.put(var, occurrences.get(var) + 1);
		}

		return super.visit(node);
	}

}
