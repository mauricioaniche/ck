package br.com.aniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.com.aniche.ck.CKNumber;
import br.com.aniche.ck.CKReport;

public class NOC extends ASTVisitor implements Metric {

	private CKReport report;

	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		ITypeBinding father = binding.getSuperclass();
		if(father!=null) {
			CKNumber fatherCk = report.getByClassName(father.getBinaryName());
			if(fatherCk!=null) fatherCk.incNoc();
		}

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKReport report) {
		this.report = report;
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
	}
}
