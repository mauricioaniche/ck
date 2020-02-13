package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.util.WordCounter;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import static com.github.mauricioaniche.ck.util.WordCounter.removeSpacesAndIdentation;

public class ClassLevelWordCounter implements CKASTVisitor, ClassLevelMetric {

	private String classSourceCode;

	public void visit(TypeDeclaration node) {

		String typeSourceCode = node.toString();
		setOrRemoveSourceCode(typeSourceCode);

	}

	public void visit(AnonymousClassDeclaration node) {
		setOrRemoveSourceCode(node.toString());

	}

	public void visit(EnumDeclaration node) {
		setOrRemoveSourceCode(node.toString());

	}

	private void setOrRemoveSourceCode(String typeSourceCode) {
		if (classSourceCode == null) {
			classSourceCode = removeSpacesAndIdentation(typeSourceCode);
		} else {
			String otherType = removeSpacesAndIdentation(typeSourceCode);
			classSourceCode = removeSpacesAndIdentation(classSourceCode.replace(otherType, ""));

		}
	}

	@Override
	public void setResult(CKClassResult result) {
		int qtyOfUniqueWords = WordCounter.wordsIn(classSourceCode).size();
		result.setUniqueWordsQty(qtyOfUniqueWords);
	}
}
