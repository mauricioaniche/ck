package br.aniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.aniche.ck.CalculatedCK;

public class DIT extends ASTVisitor implements Metric {

	private CalculatedCK result;

	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();
		
        if( binding.getSuperclass() != null ) {
//        	System.out.println("Pai: " + binding.getSuperclass().getQualifiedName());
	        ITypeBinding avo = binding.getSuperclass().getSuperclass();
			if( avo != null ) {
//	        	System.out.println(avo);
//	        	System.out.println("avô: " + binding.getSuperclass().getSuperclass().getBinaryName());
	        }
        }
		
		return super.visit(node);
	}

	@Override
	public void execute(CompilationUnit cu, CalculatedCK result) {
		
		this.result = result;
		cu.accept(this);
		
	}
}
