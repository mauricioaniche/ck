package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassLevelFieldUsageCount extends ASTVisitor implements ClassLevelMetric {
	private Set<String> declaredFields;
	private Map<String, Integer> occurrences;

	public ClassLevelFieldUsageCount() {
		declaredFields = new HashSet<>();
		this.occurrences = new HashMap<>();
	}

	public boolean visit(FieldDeclaration node) {
		for (Object obj : node.fragments()) {
			VariableDeclarationFragment field = (VariableDeclarationFragment) obj;
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
	public void execute(CompilationUnit cu, CKClassResult result) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setFieldUsage(occurrences);
	}

}
