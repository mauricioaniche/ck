package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.util.WordCounter;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import static com.github.mauricioaniche.ck.util.WordCounter.removeSpacesAndIdentation;

public class ClassLevelWordCounter extends ASTVisitor implements ClassLevelMetric {

	private String classSourceCode;

	public boolean visit(TypeDeclaration node) {

		String typeSourceCode = node.toString();
		setOrRemoveSourceCode(typeSourceCode);

		return super.visit(node);
	}

	public boolean visit(AnonymousClassDeclaration node) {
		setOrRemoveSourceCode(node.toString());

		return super.visit(node);
	}

	public boolean visit(EnumDeclaration node) {
		setOrRemoveSourceCode(node.toString());

		return super.visit(node);
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
