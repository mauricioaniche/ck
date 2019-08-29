package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ClassInfo extends ASTVisitor {

	private String className;
	private String type;
	private int modifiers;

	@Override
	public boolean visit(TypeDeclaration node) {
		
		getFullClassName(node.resolveBinding());
		
		if(node.isInterface()) type = "interface";
		else type = "class";
		this.modifiers = node.getModifiers();
		return false;
	}
	
	public int getModifiers() {
		return modifiers;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		type = "enum";
		getFullClassName(node.resolveBinding());
		this.modifiers = node.getModifiers();
		return false;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getType() {
		return type;
	}
	
	private void getFullClassName(ITypeBinding binding) {
		if(binding!=null)
			this.className = binding.getBinaryName();
	}
	
}
