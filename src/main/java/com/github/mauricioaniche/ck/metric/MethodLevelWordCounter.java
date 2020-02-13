package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.WordCounter;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import static com.github.mauricioaniche.ck.util.WordCounter.removeSpacesAndIdentation;

public class MethodLevelWordCounter implements CKASTVisitor, MethodLevelMetric {
	private String methodSourceCode;

	public void visit(MethodDeclaration node) {
		this.methodSourceCode = removeSpacesAndIdentation(node.toString());
	}

	public void visit(Initializer node) {
		this.methodSourceCode = removeSpacesAndIdentation(node.toString());
	}

	public void visit(TypeDeclaration node) {

		String otherType = removeSpacesAndIdentation(node.toString());

		methodSourceCode = removeSpacesAndIdentation(methodSourceCode.replace(otherType, ""));
	}

	@Override
	public void setResult(CKMethodResult result) {
		int qtyOfUniqueWords = WordCounter.wordsIn(methodSourceCode).size();

		result.setUniqueWordsQty(qtyOfUniqueWords);
	}


}
