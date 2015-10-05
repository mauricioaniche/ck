package br.aniche.ck.metric;

import java.util.ArrayList;
import java.util.TreeSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;

public class LCOM extends ASTVisitor implements Metric {

	ArrayList<TreeSet<String>> fields = new ArrayList<TreeSet<String>>();
	
	public boolean visit(FieldAccess node) {
		IVariableBinding binding = node.resolveFieldBinding();
		String fieldName = binding.getName();
		
		fields.get(fields.size() - 1).add(fieldName);
		
		return super.visit(node);
	}
	
	public boolean visit(MethodDeclaration node) {
		fields.add(new TreeSet<String>());
		
		return super.visit(node);
	}
	
	@Override
	public void execute(CompilationUnit cu, CKReport report) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		
		/*
		 * LCOM = |P| - |Q| if |P| - |Q| > 0
		 * where
		 * P = set of all empty set intersections
		 * Q = set of all nonempty set intersections
		 */
		// extracted from https://github.com/dspinellis/ckjm
		
		int lcom = 0;
		for (int i = 0; i < fields.size(); i++)
		    for (int j = i + 1; j < fields.size(); j++) {
				TreeSet<?> intersection = (TreeSet<?>)fields.get(i).clone();
				intersection.retainAll(fields.get(j));
				if (intersection.size() == 0) lcom++;
				else lcom--;
		    }
		result.setLcom(lcom > 0 ? lcom : 0);
	}

}
