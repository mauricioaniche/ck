package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class NumberOfMethods extends ASTVisitor implements ClassLevelMetric {

	private int methods;
	private int staticMethods;
	private int publicMethods;
	private int privateMethods;
	private int protectedMethods;
	private int defaultMethods;
	private int abstractMethods;
	private int finalMethods;
	private int synchronizedMethods;

	@Override
	public boolean visit(MethodDeclaration node) {
		methods++;

		if(Modifier.isStatic(node.getModifiers()))
			staticMethods++;

		if(Modifier.isPublic(node.getModifiers()))
			publicMethods++;

		if(Modifier.isPrivate(node.getModifiers()))
			privateMethods++;

		if(Modifier.isProtected(node.getModifiers()))
			protectedMethods++;

		if(Modifier.isDefault(node.getModifiers()))
			defaultMethods++;

		if(Modifier.isAbstract(node.getModifiers()))
			abstractMethods++;

		if(Modifier.isFinal(node.getModifiers()))
			finalMethods++;

		if(Modifier.isSynchronized(node.getModifiers()))
			synchronizedMethods++;

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setNumberOfMethods(methods);
		result.setNumberOfStaticMethods(staticMethods);
		result.setNumberOfPublicMethods(publicMethods);
		result.setNumberOfPrivateMethods(privateMethods);
		result.setNumberOfProtectedMethods(protectedMethods);
		result.setNumberOfDefaultMethods(defaultMethods);
		result.setNumberOfAbstractMethods(abstractMethods);
		result.setNumberOfFinalMethods(finalMethods);
		result.setNumberOfSynchronizedMethods(synchronizedMethods);

	}
}
