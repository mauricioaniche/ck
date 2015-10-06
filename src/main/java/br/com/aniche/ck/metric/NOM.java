package br.com.aniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public class NOM extends ASTVisitor implements Metric {

	private int methods;

	@Override
	public boolean visit(MethodDeclaration node) {
		methods++;

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKReport report) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNom(methods);
	}
}
