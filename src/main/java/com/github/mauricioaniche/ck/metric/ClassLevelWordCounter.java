package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.util.WordCounter;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import static com.github.mauricioaniche.ck.util.WordCounter.removeSpacesAndIdentation;

public class ClassLevelWordCounter extends ASTVisitor implements ClassLevelMetric {

	private String classSourceCode;

	public boolean visit(TypeDeclaration node) {

		if(classSourceCode == null) {
			classSourceCode = removeSpacesAndIdentation(node.toString());
		} else {
			String otherType = removeSpacesAndIdentation(node.toString());
			classSourceCode = removeSpacesAndIdentation(classSourceCode.replace(otherType, ""));

		}

		return super.visit(node);
	}

	@Override
	public void setResult(CKClassResult result) {
		int qtyOfUniqueWords = WordCounter.wordsIn(classSourceCode).size();
		result.setUniqueWordsQty(qtyOfUniqueWords);

	}
}
