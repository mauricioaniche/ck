package com.github.mauricioaniche.ck.metric;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.github.mauricioaniche.ck.CKClassResult;

public class NOC implements CKASTVisitor, ClassLevelMetric{

	private String name;
	private NOCExtras extras;
	
	public NOC() {
		this.extras = NOCExtras.getInstance();
	}
	
	@Override
	public void visit(TypeDeclaration node){
		ITypeBinding binding = node.resolveBinding();
		
		if(binding != null){
			this.name = binding.getQualifiedName();
			ITypeBinding father = binding.getSuperclass();
			if(father != null){
				this.extras.plusOne(father.getQualifiedName());
			}
		} else {
			this.name = node.getName().getFullyQualifiedName();
			Type type = node.getSuperclassType();
			
			SimpleType castedFatherType = null;
			
			if(node.getSuperclassType() instanceof SimpleType)
				castedFatherType = ((SimpleType) node.getSuperclassType());
			
			if(castedFatherType != null){
				this.extras.plusOne(castedFatherType.getName().getFullyQualifiedName());
			}
			List<Type> list = node.superInterfaceTypes();
			list = list.stream().filter(x -> (x instanceof SimpleType)).collect(Collectors.toList());
			list.stream().map(x -> (SimpleType) x).forEach(x -> this.extras.plusOne(x.getName().getFullyQualifiedName()));
		}
		
	}
	
	@Override
	public void setResult(CKClassResult result) {
		
	}

}
