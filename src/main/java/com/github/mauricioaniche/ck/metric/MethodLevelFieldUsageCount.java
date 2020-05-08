package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MethodLevelFieldUsageCount implements CKASTVisitor, MethodLevelMetric, VariableOrFieldMetric {
	private Set<String> declaredFields;
	private Map<String, Integer> occurrences;

	private Set<String> variables;
	private boolean isFieldAccess;
	private boolean isQualifiedName;

	public MethodLevelFieldUsageCount() {
		declaredFields = new HashSet<>();
		this.occurrences = new HashMap<>();
		this.variables = new HashSet<>();
	}

	public void visit(MethodDeclaration node) {
		IMethodBinding binding = node.resolveBinding();
		if(binding==null)
			return;

		IVariableBinding[] fields = binding.getDeclaringClass().getDeclaredFields();

		for (IVariableBinding field : fields) {
			declaredFields.add(field.getName());
		}
	}

	public void visit(VariableDeclarationFragment node) {
		String var = node.getName().toString();
		variables.add(var);
	}

	public void visit(FieldAccess node) {
		isFieldAccess = true;
	}

	public void endVisit(FieldAccess node) {
		isFieldAccess = false;
	}

	public void visit(QualifiedName node){
		isQualifiedName = true;
	}

	public void endVisit(QualifiedName node) {
		isQualifiedName = false;
	}

	private void plusOne(String var) {
		if (!occurrences.containsKey(var))
			occurrences.put(var, 0);
		occurrences.put(var, occurrences.get(var) + 1);
	}

	public void visit(SimpleName node) {
		String variableName = node.getIdentifier();

		boolean accessFieldUsingThis = isFieldAccess && declaredFields.contains(variableName);
		boolean accessFieldUsingOnlyVariableName = !isFieldAccess && declaredFields.contains(variableName) && !variables.contains(variableName);
		if((accessFieldUsingThis || accessFieldUsingOnlyVariableName) && !isQualifiedName) {
			plusOne(variableName);
		}
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setFieldUsage(occurrences);
	}
}