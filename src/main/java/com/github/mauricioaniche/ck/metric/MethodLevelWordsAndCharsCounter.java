package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.util.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.HashSet;
import java.util.List;

public class MethodLevelWordsAndCharsCounter extends ASTVisitor implements MethodLevelMetric {

	private int qtyOfDots;
	private int qtyOfParenthesis;
	private int qtyOfTabs;
	private int qtyOfSpaces;
	private int qtyOfWords;
	private int qtyOfUniqueWords;

	public boolean visit(MethodDeclaration node) {
		String methodSourceCode = node.toString();

		qtyOfDots = StringUtils.countMatches(methodSourceCode, '.');
		qtyOfParenthesis = StringUtils.countMatches(methodSourceCode, '(');
		qtyOfTabs = StringUtils.countMatches(methodSourceCode, '\t');
		qtyOfSpaces = StringUtils.countMatches(methodSourceCode, ' ');
		List<String> words = WordUtils.stringsIn(methodSourceCode);
		this.qtyOfWords = words.size();
		this.qtyOfUniqueWords = new HashSet<>(words).size();

		return super.visit(node);
	}


	@Override
	public void setResult(CKMethodResult result) {
		result.setDotsQty(qtyOfDots);
		result.setParenthesisQty(qtyOfParenthesis);
		result.setTabsQty(qtyOfTabs);
		result.setSpacesQty(qtyOfSpaces);
		result.setWordsQty(qtyOfWords);
		result.setUniqueWordsQty(qtyOfUniqueWords);

	}
}
