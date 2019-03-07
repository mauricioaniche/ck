package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import org.eclipse.jdt.core.dom.*;

public class ASTDebugger extends ASTVisitor implements ClassLevelMetric {


	public boolean visit(AnnotationTypeDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(AnnotationTypeMemberDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(AnonymousClassDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ArrayAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ArrayCreation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ArrayInitializer node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ArrayType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(AssertStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(Assignment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(Block node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(BlockComment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(BooleanLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(BreakStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(CastExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(CatchClause node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(CharacterLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ClassInstanceCreation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(CompilationUnit node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ConditionalExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ConstructorInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ContinueStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(CreationReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(Dimension node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(DoStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(EmptyStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(EnhancedForStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(EnumConstantDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(EnumDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ExpressionMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ExpressionStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(FieldAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(FieldDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ForStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(IfStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ImportDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(InfixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(Initializer node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(InstanceofExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(IntersectionType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(LabeledStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(LambdaExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(LineComment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(MarkerAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(MemberRef node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(MemberValuePair node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(MethodRef node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(MethodRefParameter node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(MethodDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(MethodInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(Modifier node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(NameQualifiedType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(NormalAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(NullLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(NumberLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(PackageDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ParameterizedType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ParenthesizedExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(PostfixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(PrefixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(PrimitiveType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(QualifiedName node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(QualifiedType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ReturnStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SimpleName node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SimpleType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SingleMemberAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SingleVariableDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(StringLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SuperConstructorInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SuperFieldAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SuperMethodInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SuperMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SwitchCase node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SwitchStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(SynchronizedStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TagElement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TextElement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ThisExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(ThrowStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TryStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TypeDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TypeDeclarationStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TypeLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TypeMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(TypeParameter node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(UnionType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(VariableDeclarationExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(VariableDeclarationStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(VariableDeclarationFragment node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(WhileStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}

	public boolean visit(WildcardType node) {
		System.out.println("-- " + node.getClass().getSimpleName()); System.out.println(node.toString()); return true;
	}



	@Override
	public void execute(CompilationUnit cu, CKClassResult result) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKClassResult result) {

	}
}
