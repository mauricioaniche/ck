package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.*;

import java.util.*;

public class IgnoreSubClasses extends ASTVisitor {

	Queue<String> currentType;
	private ASTVisitor otherVisitor;

	public IgnoreSubClasses(ASTVisitor otherVisitor) {
		this.otherVisitor = otherVisitor;
		currentType = new PriorityQueue<>();
	}

	public boolean visit(TypeDeclaration node) {
		currentType.add(node.getName().toString());

		return otherVisitor.visit(node);
	}

	public boolean visit(AnonymousClassDeclaration node) {
		currentType.add("anonymous");
		return otherVisitor.visit(node);
	}

	public boolean visit(LambdaExpression node) {
		currentType.add("lambda");
		return otherVisitor.visit(node);
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
		currentType.poll();

	}

	@Override
	public void endVisit(LambdaExpression node) {
		currentType.poll();
	}

	@Override
	public void endVisit(AnonymousClassDeclaration node) {
		currentType.poll();
	}

	private boolean isMainType() {
		return currentType.size() == 1;
	}

	public boolean visit(AnnotationTypeDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(AnnotationTypeMemberDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ArrayAccess node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ArrayCreation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ArrayInitializer node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ArrayType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(AssertStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(Assignment node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(Block node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(BlockComment node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(BooleanLiteral node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(BreakStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(CastExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(CatchClause node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(CharacterLiteral node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ClassInstanceCreation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(CompilationUnit node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ConditionalExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ConstructorInvocation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ContinueStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(CreationReference node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(Dimension node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(DoStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(EmptyStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(EnhancedForStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(EnumConstantDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(EnumDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ExpressionMethodReference node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ExpressionStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(FieldAccess node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(FieldDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ForStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(IfStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ImportDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(InfixExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(Initializer node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(InstanceofExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(IntersectionType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(LabeledStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(LineComment node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(MarkerAnnotation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(MemberRef node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(MemberValuePair node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(MethodRef node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(MethodRefParameter node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(MethodDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(MethodInvocation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(Modifier node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(NameQualifiedType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(NormalAnnotation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(NullLiteral node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(NumberLiteral node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(PackageDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ParameterizedType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ParenthesizedExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(PostfixExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(PrefixExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(PrimitiveType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(QualifiedName node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(QualifiedType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ReturnStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SimpleName node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SimpleType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SingleMemberAnnotation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SingleVariableDeclaration node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(StringLiteral node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SuperConstructorInvocation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SuperFieldAccess node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SuperMethodInvocation node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SuperMethodReference node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SwitchCase node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SwitchStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(SynchronizedStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(TagElement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(TextElement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ThisExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(ThrowStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(TryStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(TypeDeclarationStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(TypeLiteral node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(TypeMethodReference node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(TypeParameter node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(UnionType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(VariableDeclarationExpression node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(VariableDeclarationStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(VariableDeclarationFragment node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(WhileStatement node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public boolean visit(WildcardType node) {
		return isMainType() ? otherVisitor.visit(node) : true;
	}

	public void endVisit(AnnotationTypeDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(AnnotationTypeMemberDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ArrayAccess node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ArrayCreation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ArrayInitializer node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ArrayType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(AssertStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(Assignment node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(Block node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(BlockComment node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(BooleanLiteral node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(BreakStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(CastExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(CatchClause node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(CharacterLiteral node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ClassInstanceCreation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(CompilationUnit node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ConditionalExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ConstructorInvocation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ContinueStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(CreationReference node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(DoStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(EmptyStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(EnhancedForStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(EnumConstantDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(EnumDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ExpressionMethodReference node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ExpressionStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(Dimension node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(FieldAccess node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(FieldDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ForStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(IfStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ImportDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(InfixExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(InstanceofExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(Initializer node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(Javadoc node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(LabeledStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(LineComment node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(MarkerAnnotation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(MemberRef node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(MemberValuePair node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(MethodRef node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(MethodRefParameter node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(MethodDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(MethodInvocation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(Modifier node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(NameQualifiedType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(NormalAnnotation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(NullLiteral node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(NumberLiteral node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(PackageDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ParameterizedType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ParenthesizedExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(PostfixExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(PrefixExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(PrimitiveType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(QualifiedName node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(QualifiedType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ReturnStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SimpleName node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SimpleType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SingleMemberAnnotation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SingleVariableDeclaration node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(StringLiteral node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SuperConstructorInvocation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SuperFieldAccess node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SuperMethodInvocation node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SuperMethodReference node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SwitchCase node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SwitchStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(SynchronizedStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(TagElement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(TextElement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ThisExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(ThrowStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(TryStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(TypeDeclarationStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(TypeLiteral node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(TypeMethodReference node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(TypeParameter node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(UnionType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(IntersectionType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(VariableDeclarationExpression node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(VariableDeclarationStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(VariableDeclarationFragment node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(WhileStatement node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

	public void endVisit(WildcardType node) {
		if(isMainType()) otherVisitor.endVisit(node);
	}

}
