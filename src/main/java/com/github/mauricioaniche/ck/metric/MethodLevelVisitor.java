package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.JDTUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.core.dom.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.github.mauricioaniche.ck.util.JDTUtils.getMethodFullName;
import static com.github.mauricioaniche.ck.util.LOCCalculator.calculate;

public class MethodLevelVisitor extends ASTVisitor {

	private Map<String, CKMethodResult> methods;
	private Callable<List<MethodLevelMetric>> metrics;
	private CompilationUnit cu;

	private CKMethodResult currentMethod;
	private String currentMethodName;
	private List<MethodLevelMetric> currentMetricsToRun;

	public MethodLevelVisitor(Callable<List<MethodLevelMetric>> metrics, CompilationUnit cu) {
		this.metrics = metrics;
		this.cu = cu;
		methods = new HashMap<>();
	}

	public boolean visit(MethodDeclaration node) {
		currentMethodName = getMethodFullName(node);

		currentMethod = new CKMethodResult(currentMethodName, node.getModifiers());
		if(methods.containsKey(currentMethodName))
			throw new RuntimeException("Method " + currentMethodName + " already visited. This might happen when methods have same name and types only differ because of generics.");

		methods.put(currentMethodName, currentMethod);

		currentMethod.setLoc(calculate(IOUtils.toInputStream(node.toString())));
		currentMethod.setStartLine(JDTUtils.getStartLine(cu, node));

		try {
			currentMetricsToRun = metrics.call();
			if(currentMetricsToRun!=null) currentMetricsToRun.stream().forEach(ast -> ast.visit(node));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return super.visit(node);
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		currentMetricsToRun.forEach(m -> m.setResult(currentMethod));
	}

	@Override
	public void endVisit(Block node) {
		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.endVisit(node));
	}

	@Override
	public void endVisit(FieldAccess node) {
		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.endVisit(node));
	}

	// TODO: add all other endVisit method invocations here.

	public Map<String, CKMethodResult> getMap() {
		return Collections.unmodifiableMap(methods);
	}


	public boolean visit(AnnotationTypeDeclaration node) {
		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;
	}
	public boolean visit(AnnotationTypeMemberDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(AnonymousClassDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ArrayAccess node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ArrayCreation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ArrayInitializer node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ArrayType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(AssertStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(Assignment node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(Block node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(BlockComment node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(BooleanLiteral node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(BreakStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(CastExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(CatchClause node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(CharacterLiteral node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ClassInstanceCreation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(CompilationUnit node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ConditionalExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ConstructorInvocation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ContinueStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(CreationReference node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(Dimension node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(DoStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(EmptyStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(EnhancedForStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(EnumConstantDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(EnumDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ExpressionMethodReference node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ExpressionStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(FieldAccess node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(FieldDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ForStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(IfStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ImportDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(InfixExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(Initializer node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(InstanceofExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(IntersectionType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(LabeledStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(LambdaExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(LineComment node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(MarkerAnnotation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(MemberRef node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(MemberValuePair node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(MethodRef node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(MethodRefParameter node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(MethodInvocation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(Modifier node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(NameQualifiedType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(NormalAnnotation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(NullLiteral node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(NumberLiteral node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(PackageDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ParameterizedType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ParenthesizedExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(PostfixExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(PrefixExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(PrimitiveType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(QualifiedName node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(QualifiedType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ReturnStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SimpleName node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SimpleType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SingleMemberAnnotation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SingleVariableDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(StringLiteral node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SuperConstructorInvocation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SuperFieldAccess node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SuperMethodInvocation node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SuperMethodReference node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SwitchCase node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SwitchStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(SynchronizedStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TagElement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TextElement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ThisExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(ThrowStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TryStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TypeDeclaration node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TypeDeclarationStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TypeLiteral node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TypeMethodReference node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(TypeParameter node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(UnionType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(VariableDeclarationExpression node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(VariableDeclarationStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(VariableDeclarationFragment node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(WhileStatement node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}

	public boolean visit(WildcardType node) {

		if(currentMetricsToRun!=null) currentMetricsToRun.stream().map(metric -> (ASTVisitor) metric).forEach(ast -> ast.visit(node));
		return true;

	}
}
