package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class NumberOfFields extends ASTVisitor implements ClassLevelMetric {

	private int fields;
	private int staticFields;
	private int publicFields;
	private int privateFields;
	private int protectedFields;
	private int defaultFields;
	private int finalFields;
	private int synchronizedFields;

	@Override
	public boolean visit(FieldDeclaration node) {
		fields++;

		if(Modifier.isStatic(node.getModifiers()))
			staticFields++;

		if(Modifier.isPublic(node.getModifiers()))
			publicFields++;

		if(Modifier.isPrivate(node.getModifiers()))
			privateFields++;

		if(Modifier.isProtected(node.getModifiers()))
			protectedFields++;

		if(Modifier.isDefault(node.getModifiers()))
			defaultFields++;

		if(Modifier.isFinal(node.getModifiers()))
			finalFields++;

		if(Modifier.isSynchronized(node.getModifiers()))
			synchronizedFields++;

		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult number) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setNumberOfFields(fields);
		result.setNumberOfStaticFields(staticFields);
		result.setNumberOfPublicFields(publicFields);
		result.setNumberOfPrivateFields(privateFields);
		result.setNumberOfProtectedFields(protectedFields);
		result.setNumberOfDefaultFields(defaultFields);
		result.setNumberOfFinalFields(finalFields);
		result.setNumberOfSynchronizedFields(synchronizedFields);
	}
}
