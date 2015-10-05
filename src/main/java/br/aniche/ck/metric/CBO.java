package br.aniche.ck.metric;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;

public class CBO extends ASTVisitor implements Metric {

	private Set<String> coupling = new HashSet<String>();
	
	@Override
	public boolean visit(VariableDeclarationStatement node) {
		coupleTo(node.getType().resolveBinding());
		return super.visit(node);
	}

	@Override
	public boolean visit(ArrayCreation node) {
		coupleTo(node.getType().resolveBinding());
		return super.visit(node);
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		coupleTo(node.getType().resolveBinding());
		return super.visit(node);
	}
	
	public boolean visit(ReturnStatement node) {
		if(node.getExpression()!=null) {
			coupleTo(node.getExpression().resolveTypeBinding());
		}
		return super.visit(node);
	}

	public boolean visit(MethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		if(binding!=null) {
			for(ITypeBinding t : binding.getParameterTypes()) {
				coupleTo(t);
			}
		}
		
		return super.visit(node);
	}
	
	public boolean visit(ThrowStatement node) {
		coupleTo(node.getExpression().resolveTypeBinding());
		return super.visit(node);
	}
	
	public boolean visit(TypeDeclaration node) {
		ITypeBinding type = node.resolveBinding();
		
		ITypeBinding binding = type.getSuperclass();
		if(binding!=null) coupleTo(binding);
		
		for(ITypeBinding interfaces : type.getInterfaces()) {
			coupleTo(interfaces);
		}
		
		return super.visit(node);
	}
	
	public boolean visit(MethodDeclaration node) {
		
		IMethodBinding method = node.resolveBinding();
		
		coupleTo(method.getReturnType());
		
		for(ITypeBinding param : method.getParameterTypes()) {
			coupleTo(param);
		}
		
		return super.visit(node);
	}
	
	@Override
	public boolean visit(CastExpression node) {
		coupleTo(node.getType().resolveBinding());
		
		return super.visit(node);
	}
	
	@Override
	public boolean visit(InstanceofExpression node) {
		
		coupleTo(node.getRightOperand().resolveBinding());
		coupleTo(node.getLeftOperand().resolveTypeBinding());
		
		return super.visit(node);
	}
	
	public boolean visit(NormalAnnotation node) {
		coupleTo(node.resolveTypeBinding());
		return super.visit(node);
	}
	
	public boolean visit(MarkerAnnotation node) {
		coupleTo(node.resolveTypeBinding());
		return super.visit(node);
	}

	public boolean visit(SingleMemberAnnotation node) {
		coupleTo(node.resolveTypeBinding());
		return super.visit(node);
	}
	
	private void coupleTo(ITypeBinding binding) {
		if(binding==null) return;
		
		String type = binding.getQualifiedName();
		if(!binding.isPrimitive()) coupling.add(type.replace("[]", ""));
	}

	@Override
	public void execute(CompilationUnit cu, CKReport report) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setCbo(coupling.size());
		
	}
}
