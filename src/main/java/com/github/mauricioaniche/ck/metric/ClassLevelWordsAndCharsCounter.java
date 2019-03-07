package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.util.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.HashSet;
import java.util.List;

public class ClassLevelWordsAndCharsCounter extends ASTVisitor implements ClassLevelMetric {

	private int qtyOfDots;
	private int qtyOfParenthesis;
	private int qtyOfTabs;
	private int qtyOfSpaces;
	private int qtyOfWords;
	private int qtyOfUniqueWords;

	private boolean visitedTypeAlready = false;

	public boolean visit(TypeDeclaration node) {

		if(visitedTypeAlready) return super.visit(node);

		String methodSourceCode = node.toString();

		qtyOfDots = StringUtils.countMatches(methodSourceCode, '.');
		qtyOfParenthesis = StringUtils.countMatches(methodSourceCode, '(');
		qtyOfTabs = StringUtils.countMatches(methodSourceCode, '\t');
		qtyOfSpaces = StringUtils.countMatches(methodSourceCode, ' ');
		List<String> words = WordUtils.stringsIn(methodSourceCode);
		this.qtyOfWords = words.size();
		this.qtyOfUniqueWords = new HashSet<>(words).size();


		visitedTypeAlready = true;
		return super.visit(node);

	}

	@Override
	public void execute(CompilationUnit cu, CKClassResult result) {
		cu.accept(new IgnoreSubClasses(this));
	}

	@Override
	public void setResult(CKClassResult result) {

		result.setDotsQty(qtyOfDots);
		result.setParenthesisQty(qtyOfParenthesis);
		result.setTabsQty(qtyOfTabs);
		result.setSpacesQty(qtyOfSpaces);
		result.setWordsQty(qtyOfWords);
		result.setUniqueWordsQty(qtyOfUniqueWords);

	}
}
