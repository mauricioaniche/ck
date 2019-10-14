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


		boolean isPublic = Modifier.isPublic(node.getModifiers());
		boolean isPrivate = Modifier.isPrivate(node.getModifiers());
		boolean isProtected = Modifier.isProtected(node.getModifiers());

		if(isPublic)
			publicFields++;
		else if(isPrivate)
			privateFields++;
		else if(isProtected)
			protectedFields++;
		else
			defaultFields++;

		// other characteristics rather than visibility
		boolean isStatic = Modifier.isStatic(node.getModifiers());
		boolean isFinal = Modifier.isFinal(node.getModifiers());
		boolean isSynchronized = Modifier.isSynchronized(node.getModifiers());
		
		if(isStatic)
			staticFields++;

		if(isFinal)
			finalFields++;

		if(isSynchronized)
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
