package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CBO implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private Set<String> coupling = new HashSet<String>();

	@Override
	public void visit(VariableDeclarationStatement node) {
		coupleTo(node.getType());
	}

	@Override
	public void visit(ClassInstanceCreation node) {
		coupleTo(node.getType());
	}

	@Override
	public void visit(ArrayCreation node) {
		coupleTo(node.getType());
	}

	@Override
	public void visit(FieldDeclaration node) {
		coupleTo(node.getType());
	}

	public void visit(ReturnStatement node) {
		if (node.getExpression() != null) {
			coupleTo(node.getExpression().resolveTypeBinding());
		}
	}

	@Override
	public void visit(TypeLiteral node) {
		coupleTo(node.getType());
	}
	
	public void visit(ThrowStatement node) {
		if(node.getExpression()!=null)
			coupleTo(node.getExpression().resolveTypeBinding());
	}

	public void visit(TypeDeclaration node) {
		ITypeBinding resolvedType = node.resolveBinding();

		if(resolvedType!=null) {
			ITypeBinding binding = resolvedType.getSuperclass();
			if (binding != null)
				coupleTo(binding);

			for (ITypeBinding interfaces : resolvedType.getInterfaces()) {
				coupleTo(interfaces);
			}
		} else {
			coupleTo(node.getSuperclassType());
			List<Type> list = node.superInterfaceTypes();
			list.forEach(x -> coupleTo(x));
		}

	}

	public void visit(MethodDeclaration node) {

		IMethodBinding resolvedMethod = node.resolveBinding();
		if (resolvedMethod != null) {

			coupleTo(resolvedMethod.getReturnType());

			for (ITypeBinding param : resolvedMethod.getParameterTypes()) {
				coupleTo(param);
			}
		} else {
			coupleTo(node.getReturnType2());
			List<TypeParameter> list = node.typeParameters();
			list.forEach(x -> coupleTo(x.getName()));
		}

	}

	@Override
	public void visit(CastExpression node) {
		coupleTo(node.getType());

	}

	@Override
	public void visit(InstanceofExpression node) {

		coupleTo(node.getRightOperand());
		coupleTo(node.getLeftOperand().resolveTypeBinding());

	}

	@Override
	public void visit(MethodInvocation node) {

		IMethodBinding binding = node.resolveMethodBinding();
		if(binding!=null)
			coupleTo(binding.getDeclaringClass());

	}

	public void visit(NormalAnnotation node) {
		coupleTo(node);
	}

	public void visit(MarkerAnnotation node) {
		coupleTo(node);
	}

	public void visit(SingleMemberAnnotation node) {
		coupleTo(node);
	}

	public void visit(ParameterizedType node) {
		try {
			ITypeBinding binding = node.resolveBinding();
			if (binding != null) {
	
				coupleTo(binding);
	
				for (ITypeBinding types : binding.getTypeArguments()) {
					coupleTo(types);
				}
			} else {
				coupleTo(node.getType());
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

	}
	private void coupleTo(Annotation type) {
		ITypeBinding resolvedType = type.resolveTypeBinding();
		if(resolvedType!=null)
			coupleTo(resolvedType);
		else {
			addToSet(type.getTypeName().getFullyQualifiedName());
		}
	}

	private void coupleTo(Type type) {
		if(type==null)
			return;

		ITypeBinding resolvedBinding = type.resolveBinding();
		if(resolvedBinding!=null)
			coupleTo(resolvedBinding);
		else {
			if(type instanceof SimpleType) {
				SimpleType castedType = (SimpleType) type;
				addToSet(castedType.getName().getFullyQualifiedName());
			}
			else if(type instanceof QualifiedType) {
				QualifiedType castedType = (QualifiedType) type;
				addToSet(castedType.getName().getFullyQualifiedName());
			}
			else if(type instanceof NameQualifiedType) {
				NameQualifiedType castedType = (NameQualifiedType) type;
				addToSet(castedType.getName().getFullyQualifiedName());
			}
			else if(type instanceof ParameterizedType) {
				ParameterizedType castedType = (ParameterizedType) type;
				coupleTo(castedType.getType());
			}
			else if(type instanceof WildcardType) {
				WildcardType castedType = (WildcardType) type;
				coupleTo(castedType.getBound());
			}
			else if(type instanceof ArrayType) {
				ArrayType castedType = (ArrayType) type;
				coupleTo(castedType.getElementType());
			}
			else if(type instanceof IntersectionType) {
				IntersectionType castedType = (IntersectionType) type;
				List<Type> types = castedType.types();
				types.stream().forEach(x -> coupleTo(x));
			}
			else if(type instanceof UnionType) {
				UnionType castedType = (UnionType) type;
				List<Type> types = castedType.types();
				types.stream().forEach(x -> coupleTo(x));
			}
		}
	}

	private void coupleTo(SimpleName name) {
		addToSet(name.getFullyQualifiedName());
	}

	private void coupleTo(ITypeBinding binding) {

		if (binding == null)
			return;
		if (binding.isWildcardType())
			return;
		if (binding.isNullType())
			return;

		String type = binding.getQualifiedName();
		if (type.equals("null"))
			return;

		if (isFromJava(type) || binding.isPrimitive())
			return;


		String cleanedType = cleanClassName(type);
		addToSet(cleanedType);
	}

	private String cleanClassName(String type) {
		// remove possible array(s) in the class name
		String cleanedType = type.replace("[]", "").replace("\\$", ".");

		// remove generics declaration, let's stype with the type
		if(cleanedType.contains("<"))
			cleanedType = cleanedType.substring(0, cleanedType.indexOf("<"));

		return cleanedType;
	}

	private boolean isFromJava(String type) {
		return type.startsWith("java.") || type.startsWith("javax.");
	}

	private void addToSet(String name) {
		this.coupling.add(name);
	}

	@Override
	public void setResult(CKClassResult result) {
		clean();
		result.setCbo(getValue());
	}

	// given that some resolvings might fail, we remove types that might
	// had appeared here twice.
	// e.g. if the set contains 'A.B.Class' and 'Class', it is likely that
	// 'Class' == 'A.B.Class'
	private void clean() {
		Set<String> singleQualifiedTypes = coupling.stream().filter(x -> !x.contains(".")).collect(Collectors.toSet());

		for(String singleQualifiedType : singleQualifiedTypes) {
			long count = coupling.stream().filter(x -> x.endsWith("." + singleQualifiedType)).count();

			boolean theSameFullyQualifiedTypeExists = count > 0;
			if(theSameFullyQualifiedTypeExists)
				coupling.remove(singleQualifiedType);
		}
	}

	@Override
	public void setResult(CKMethodResult result) {
		clean();
		result.setCbo(getValue());
	}

	private int getValue() {
		return coupling.size();
	}
}
