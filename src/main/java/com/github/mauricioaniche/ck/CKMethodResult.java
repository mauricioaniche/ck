package com.github.mauricioaniche.ck;

import java.util.Map;

public class CKMethodResult {
	private int cbo;
	private int rfc;
	private int wmc;
	private String methodName;
	private int parametersQty;
	private int returnQty;
	private int loc;
	private int variablesQty;
	private Map<String, Integer> variablesUsage;
	private int startLine;
	private int loopQty;
	private int comparisonsQty;
	private int tryCatchQty;
	private int parenthesizedExpsQty;
	private int stringLiteralsQty;
	private int numbersQty;
	private int assignmentsQty;
	private int mathOperationsQty;
	private int maxNestedBlocks;
	private int anonymousClassesQty;
	private int subClassesQty;
	private int lambdasQty;
	private int uniqueWordsQty;
	private Map<String, Integer> fieldUsage;
	private int modifiers;

	public CKMethodResult(String methodName, int modifiers) {
		this.methodName = methodName;
		this.modifiers = modifiers;
	}

	/**
	 * public/static/private and other org.eclipse.jdt.core.dom.Modifier modifiers
	 * 
	 * @see org.eclipse.jdt.core.dom.Modifier to decode int
	 * @return
	 */
	public int getModifiers() {
		return modifiers;
	}
	
	public void setCbo(int cbo) {
		this.cbo = cbo;
	}

	public void setRfc(int rfc) {
		this.rfc = rfc;
	}

	public void setWmc(int wmc) {
		this.wmc = wmc;
	}

	public void setParametersQty(int parametersQty) {
		this.parametersQty = parametersQty;
	}

	public int getCbo() {
		return cbo;
	}

	public int getRfc() {
		return rfc;
	}

	public int getWmc() {
		return wmc;
	}

	public String getMethodName() {
		return methodName;
	}

	@Override
	public String toString() {
		return "CKMethodResult{" +
				"cbo=" + cbo +
				", rfc=" + rfc +
				", wmc=" + wmc +
				", methodName='" + methodName + '\'' +
				'}';
	}

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public void setVariablesQty(int variablesQty) {
		this.variablesQty = variablesQty;
	}

	public int getLoc() {
		return loc;
	}

	public int getParametersQty() {
		return parametersQty;
	}

	public int getVariablesQty() {
		return variablesQty;
	}

	public int getReturnQty() {
		return returnQty;
	}

	public void setVariablesUsage(Map<String, Integer> variablesUsage) {
		this.variablesUsage = variablesUsage;
	}

	public Map<String, Integer> getVariablesUsage() {
		return variablesUsage;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setLoopQty(int loopQty) {
		this.loopQty = loopQty;
	}

	public int getLoopQty() {
		return loopQty;
	}

	public void setComparisonsQty(int comparisonsQty) {
		this.comparisonsQty = comparisonsQty;
	}

	public int getComparisonsQty() {
		return comparisonsQty;
	}

	public void setTryCatchQty(int tryCatchQty) {
		this.tryCatchQty = tryCatchQty;
	}

	public int getTryCatchQty() {
		return tryCatchQty;
	}

	public void setParenthesizedExpsQty(int parenthesizedExpsQty) {
		this.parenthesizedExpsQty = parenthesizedExpsQty;
	}

	public int getParenthesizedExpsQty() {
		return parenthesizedExpsQty;
	}

	public void setStringLiteralsQty(int stringsQty) {
		this.stringLiteralsQty = stringsQty;
	}

	public int getStringLiteralsQty() {
		return stringLiteralsQty;
	}

	public void setNumbersQty(int numbersQty) {
		this.numbersQty = numbersQty;
	}

	public void setAssignmentsQty(int assignmentsQty) {
		this.assignmentsQty = assignmentsQty;
	}

	public int getAssignmentsQty() {
		return assignmentsQty;
	}

	public void setMathOperationsQty(int mathOperationsQty) {
		this.mathOperationsQty = mathOperationsQty;
	}

	public int getMathOperationsQty() {
		return mathOperationsQty;
	}

	public void setMaxNestedBlocks(int maxNestedBlocks) {
		this.maxNestedBlocks = maxNestedBlocks;
	}

	public int getMaxNestedBlocks() {
		return maxNestedBlocks;
	}

	public int getNumbersQty() {
		return numbersQty;
	}

	public void setAnonymousClassesQty(int anonymousClassesQty) {
		this.anonymousClassesQty = anonymousClassesQty;
	}

	public int getAnonymousClassesQty() {
		return anonymousClassesQty;
	}

	public void setSubClassesQty(int subClassesQty) {
		this.subClassesQty = subClassesQty;
	}

	public int getSubClassesQty() {
		return subClassesQty;
	}

	public void setLambdasQty(int lambdasQty) {
		this.lambdasQty = lambdasQty;
	}

	public int getLambdasQty() {
		return lambdasQty;
	}

	public void setUniqueWordsQty(int uniqueWordsQty) {
		this.uniqueWordsQty = uniqueWordsQty;
	}

	public int getUniqueWordsQty() {
		return uniqueWordsQty;
	}

	public void setFieldUsage(Map<String, Integer> fieldUsage) {
		this.fieldUsage = fieldUsage;
	}

	public Map<String, Integer> getFieldUsage() {
		return fieldUsage;
	}
}
