package br.com.aniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ClassInfo extends ASTVisitor {

	private String className;

	@Override
	public boolean visit(TypeDeclaration node) {
		
		getFullClassName(node.resolveBinding());
		return false;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		getFullClassName(node.resolveBinding());
		return false;
	}
	
	public String getClassName() {
		return className;
	}
	
	private void getFullClassName(ITypeBinding binding) {
		if(binding!=null)
			this.className = binding.getBinaryName();
	}
	
}
