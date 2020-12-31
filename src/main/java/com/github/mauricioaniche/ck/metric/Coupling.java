package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.JDTUtils;

import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Coupling implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private CouplingExtras extras;
	private String className;
	private String methodName;
	
	public Coupling() {
		this.extras = CouplingExtras.getInstance();
	}

	@Override
	public void visit(VariableDeclarationStatement node) {
		if(this.className != null) {
			coupleTo(node.getType());
		}
	}

	@Override
	public void visit(ClassInstanceCreation node) {
		if(this.className != null) {
			coupleTo(node.getType());
		} else if(this.methodName != null) {	
			IMethodBinding binding = node.resolveConstructorBinding();
			coupleTo(binding);
		} 
	}

	@Override
	public void visit(ArrayCreation node) {
		if(this.className != null) {
			coupleTo(node.getType());
		}
	}

	@Override
	public void visit(FieldDeclaration node) {
		if(this.className != null) {
			coupleTo(node.getType());
		}
	}

	public void visit(ReturnStatement node) {
		if(this.className != null){
			if (node.getExpression() != null) {
				coupleTo(node.getExpression().resolveTypeBinding());
			}
		}
	}

	@Override
	public void visit(TypeLiteral node) {
		if(this.className != null) {
			coupleTo(node.getType());
		}
	}
	
	public void visit(ThrowStatement node) {
		if(this.className != null) {
			if(node.getExpression()!=null)
				coupleTo(node.getExpression().resolveTypeBinding());
		}
	}

	public void visit(TypeDeclaration node) {
		if(this.className != null) {
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

	}

	public void visit(MethodDeclaration node) {
		if(this.className != null) {
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

	}

	@Override
	public void visit(CastExpression node) {
		if(this.className != null) {
			coupleTo(node.getType());
		}

	}

	@Override
	public void visit(InstanceofExpression node) {
		if(this.className != null) {
			coupleTo(node.getRightOperand());
			coupleTo(node.getLeftOperand().resolveTypeBinding());
		}

	}

	@Override
	public void visit(MethodInvocation node) {
		
		IMethodBinding binding = node.resolveMethodBinding();
		if(binding!=null) {
			if(this.className != null) {
				coupleTo(binding.getDeclaringClass());
			} else if(this.methodName != null) {
				coupleTo(binding);
			}
		}

	}

	public void visit(NormalAnnotation node) {
		if(this.className != null) {
			coupleTo(node);
		}
	}

	public void visit(MarkerAnnotation node) {
		if(this.className != null) {
			coupleTo(node);
		}
	}

	public void visit(SingleMemberAnnotation node) {
		if(this.className != null) {
			coupleTo(node);
		}
	}

	public void visit(ParameterizedType node) {
		if(this.className != null) {
			
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

	}
	private void coupleTo(Annotation type) {
		if(this.className != null) {
			ITypeBinding resolvedType = type.resolveTypeBinding();
			if(resolvedType!=null)
				coupleTo(resolvedType);
			else {
				addToSet(type.getTypeName().getFullyQualifiedName());
			}
		}
	}

	private void coupleTo(Type type) {
		if(type==null)
			return;

		if(this.className != null) {
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
	}

	private void coupleTo(SimpleName name) {
		if(this.className != null) {
			addToSet(name.getFullyQualifiedName());
		}
	}

	private void coupleTo(ITypeBinding binding) {

		if(this.className != null) {
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
	}
	
	private void coupleTo(IMethodBinding binding) {
		
		if(binding == null)
			return;
		
		String methodNameInvoked = JDTUtils.getQualifiedMethodFullName(binding);
		
		if (methodNameInvoked.equals("null"))
			return;

		if (isFromJava(methodNameInvoked))
			return;
		
		addToSet(methodNameInvoked);
		
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
		if(className != null){
			this.extras.addToSetClassIn(name, this.className);
			this.extras.addToSetClassOut(this.className, name);
		} else {
			this.extras.addToSetMethodIn(name, this.methodName);
			this.extras.addToSetMethodOut(this.methodName, name);
		}
	}

	@Override
	public void setResult(CKClassResult result) {
		
	}

	@Override
	public void setResult(CKMethodResult result) {
		
	}
	
	@Override
	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
