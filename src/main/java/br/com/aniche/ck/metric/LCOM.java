package br.com.aniche.ck.metric;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public class LCOM extends ASTVisitor implements Metric {

	ArrayList<TreeSet<String>> fields = new ArrayList<TreeSet<String>>();
	Set<String> declaredFields;
	
	public LCOM() {
		this.declaredFields = new HashSet<String>();
	}
	
	public boolean visit(FieldDeclaration node) {
		
		for(Object o : node.fragments()) {
			VariableDeclarationFragment vdf = (VariableDeclarationFragment) o;
			declaredFields.add(vdf.getName().toString());
		}
		
		return super.visit(node);
	}
	
	public boolean visit(SimpleName node) {
		String name = node.getFullyQualifiedName();
		if(declaredFields.contains(name)) {
			acessed(name);
		}
		
		return super.visit(node);
	}

	private void acessed(String name) {
		if(!fields.isEmpty()){
			fields.get(fields.size() - 1).add(name);
		}
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
