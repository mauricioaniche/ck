package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.WordCounter;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import static com.github.mauricioaniche.ck.util.WordCounter.removeSpacesAndIdentation;

public class MethodLevelWordCounter extends ASTVisitor implements MethodLevelMetric {
	private String methodSourceCode;

	public boolean visit(MethodDeclaration node) {
		this.methodSourceCode = removeSpacesAndIdentation(node.toString());

		return super.visit(node);
	}

	public boolean visit(Initializer node) {
		this.methodSourceCode = removeSpacesAndIdentation(node.toString());

		return super.visit(node);
	}

	public boolean visit(TypeDeclaration node) {

		String otherType = removeSpacesAndIdentation(node.toString());

		methodSourceCode = removeSpacesAndIdentation(methodSourceCode.replace(otherType, ""));

		return super.visit(node);
	}

	@Override
	public void setResult(CKMethodResult result) {
		int qtyOfUniqueWords = WordCounter.wordsIn(methodSourceCode).size();

		result.setUniqueWordsQty(qtyOfUniqueWords);
	}


}
