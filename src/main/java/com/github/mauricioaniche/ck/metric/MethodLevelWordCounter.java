package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.WordUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.HashSet;
import java.util.List;

public class MethodLevelWordCounter extends ASTVisitor implements MethodLevelMetric {
	private int qtyOfUniqueWords;

	public boolean visit(MethodDeclaration node) {
		String methodSourceCode = node.toString();

		this.qtyOfUniqueWords = WordUtils.wordsIn(methodSourceCode).size();

		return super.visit(node);
	}


	@Override
	public void setResult(CKMethodResult result) {
		result.setUniqueWordsQty(qtyOfUniqueWords);
	}
}
