package com.github.mauricioaniche.ck;

import org.eclipse.jdt.core.dom.Modifier;

import com.github.mauricioaniche.ck.metric.CouplingExtras;

import java.util.*;

public class CKMethodResult {
	private int cbo;
	private int cboModified = -1;
	private int fanin = -1;
	private int fanout = -1;
	private int rfc;
	private int wmc;
	private String methodName;
	private String qualifiedMethodName;
	private boolean isVisible;
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
	private int innerClassesQty;
	private int lambdasQty;
	private int uniqueWordsQty;
	//all local field accesses
	private Map<String, Integer> fieldUsage;
	private boolean isConstructor;
	private int modifiers;
	private int logStatementsQty;
	private boolean hasJavadoc;

	//All direct invocations of methods
	private Set<String> methodInvocations;
	//All direct invocations of methods of the same class
	private Set<String> methodInvocationsLocal;
	//All indirect invocations of methods of the same class
	private Map<String, Set<String>> methodInvocationsIndirectLocal;

	public CKMethodResult(String methodName, String qualifiedMethodName, boolean isConstructor, int modifiers) {
		this.methodName = methodName;
		this.qualifiedMethodName = qualifiedMethodName;
		this.isConstructor = isConstructor;
		this.modifiers = modifiers;
		this.isVisible = !Modifier.isPrivate(modifiers);
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

	public boolean getIsVisible(){ return isVisible;}

	public void setCbo(int cbo) {
		this.cbo = cbo;
	}
	
	public void setCboModified(int cboModified) {
		this.cboModified = cboModified;
	}
	
	public int getCboModified() {
		if(this.cboModified == -1){
			CouplingExtras extras = CouplingExtras.getInstance();
			this.setCboModified(extras.getValueCBOMethod(this.qualifiedMethodName));
		}
		return cboModified;
	}
	
	public void setFanin(int fanin) {
		this.fanin = fanin;
	}
	
	public int getFanin() {
		if(this.fanin == -1){
			CouplingExtras extras = CouplingExtras.getInstance();
			this.setFanin(extras.getValueFanInMethod(this.qualifiedMethodName));
		}
		return fanin;
	}
	
	public void setFanout(int fanout) {
		this.fanout = fanout;
	}
	
	public int getFanout() {
		if(this.fanout == -1){
			CouplingExtras extras = CouplingExtras.getInstance();
			this.setFanout(extras.getValueFanOutMethod(this.qualifiedMethodName));
		}
		return fanout;
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

	public String getQualifiedMethodName() {
		return qualifiedMethodName;
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
		if(this.variablesUsage==null)
			this.variablesUsage = new HashMap<>();

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

	public void setInnerClassesQty(int innerClassesQty) {
		this.innerClassesQty = innerClassesQty;
	}

	public int getInnerClassesQty() {
		return innerClassesQty;
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
		if(this.fieldUsage==null)
			fieldUsage = new HashMap<>();

		return fieldUsage;
	}

	public Set<String> getFieldsAccessed() {
		if(this.fieldUsage==null)
			fieldUsage = new HashMap<>();

		return fieldUsage.keySet();
	}

	public boolean isConstructor() {
		return isConstructor;
	}

	public int getLogStatementsQty() {
		return logStatementsQty;
	}

	public void setLogStatementsQty(int logStatementsQty) {
		this.logStatementsQty = logStatementsQty;
	}

	public void setHasJavadoc(boolean hasJavadoc) {
		this.hasJavadoc = hasJavadoc;
	}

	public boolean getHasJavadoc() {
		return hasJavadoc;
	}

	public void setMethodInvocations(Set<String> methodInvocations) {
		this.methodInvocations = methodInvocations;
	}

	public Set<String> getMethodInvocations() {
		if(methodInvocations==null)
			methodInvocations = new HashSet<>();

		return methodInvocations;
	}

	public void setMethodInvocationLocal(Set<String> methodInvocationsLocal) {
		this.methodInvocationsLocal = methodInvocationsLocal;
	}

	public Set<String> getMethodInvocationsLocal() {
		return methodInvocationsLocal;
	}

	public void setMethodInvocationsIndirectLocal(Map<String, Set<String>> methodInvocationsIndirect) {
		this.methodInvocationsIndirectLocal = methodInvocationsIndirect;
	}

	public Map<String, Set<String>> getMethodInvocationsIndirectLocal() {
		return methodInvocationsIndirectLocal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CKMethodResult that = (CKMethodResult) o;
		return startLine == that.startLine &&
				methodName.equals(that.methodName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(methodName, startLine);
	}

}
