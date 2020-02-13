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
			declaredFields.add(field.getName().toString());
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

	private void addField(String var) {
		if (!occurrences.containsKey(var))
			occurrences.put(var, 0);
	}

	private void plusOne(String var) {
		addField(var);
		occurrences.put(var, occurrences.get(var) + 1);
	}

	public void visit(SimpleName node) {

		String var = node.getIdentifier();

		if(isFieldAccess)
			addField(var);

		boolean accessFieldUsingThis = isFieldAccess && declaredFields.contains(var);
		boolean accessFieldUsingOnlyVariableName = !isFieldAccess && declaredFields.contains(var) && !variables.contains(var);

		if(accessFieldUsingThis || accessFieldUsingOnlyVariableName) {
			plusOne(var);
		}

	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setFieldUsage(occurrences);
	}
}
