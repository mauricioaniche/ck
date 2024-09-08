package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.CKASTVisitor;
import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import org.eclipse.jdt.core.dom.*;

public class ASTDebugger implements CKASTVisitor, ClassLevelMetric {


	public void visit(AnnotationTypeDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(AnnotationTypeMemberDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(AnonymousClassDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ArrayAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ArrayCreation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ArrayInitializer node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ArrayType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(AssertStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(Assignment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(Block node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(BlockComment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(BooleanLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(BreakStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(CastExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(CatchClause node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(CharacterLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ClassInstanceCreation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(CompilationUnit node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ConditionalExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ConstructorInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ContinueStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(CreationReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(Dimension node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(DoStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(EmptyStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(EnhancedForStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(EnumConstantDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(EnumDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ExpressionMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ExpressionStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(FieldAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(FieldDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ForStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(IfStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ImportDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(InfixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(Initializer node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(InstanceofExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(IntersectionType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(LabeledStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(LambdaExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(LineComment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(MarkerAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(MemberRef node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(MemberValuePair node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(MethodRef node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(MethodRefParameter node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(MethodDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(MethodInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(Modifier node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(NameQualifiedType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(NormalAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(NullLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(NumberLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(PackageDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ParameterizedType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ParenthesizedExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(PostfixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(PrefixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(PrimitiveType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(QualifiedName node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(QualifiedType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ReturnStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SimpleName node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SimpleType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SingleMemberAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SingleVariableDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(StringLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SuperConstructorInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SuperFieldAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SuperMethodInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SuperMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SwitchCase node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SwitchStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(SynchronizedStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TagElement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TextElement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ThisExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(ThrowStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TryStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TypeDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TypeDeclarationStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TypeLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TypeMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(TypeParameter node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(UnionType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(VariableDeclarationExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(VariableDeclarationStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(VariableDeclarationFragment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(WhileStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}

	public void visit(WildcardType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString());
	}



	@Override
	public void setResult(CKClassResult result) {

	}
}
