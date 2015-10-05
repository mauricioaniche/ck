package br.aniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.aniche.ck.CKNumber;

public class DIT extends ASTVisitor implements Metric {

	int dit = 0;

	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		calculate(binding);

		return super.visit(node);
	}

	private void calculate(ITypeBinding binding) {
		ITypeBinding father = binding.getSuperclass();
		if (father != null) {
			String fatherName = father.getQualifiedName();
			if (fatherName.endsWith("Object")) return;
			dit++;

			calculate(father);
		}

	}

	@Override
	public void execute(CompilationUnit cu) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setDit(dit);
	}
}
