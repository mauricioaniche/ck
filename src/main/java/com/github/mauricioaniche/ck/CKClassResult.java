package com.github.mauricioaniche.ck;

import java.util.*;

import com.github.mauricioaniche.ck.metric.CouplingExtras;
import com.github.mauricioaniche.ck.metric.NOCExtras;

public class CKClassResult {

	private String file;
	private String className;
	private String type;

	private int dit;
	private int noc = -1;
	private int wmc;
	private int cbo;
	private int cboModified = -1;
	private int fanin = -1;
	private int fanout = -1;
	private int lcom;
	private float lcomNormalized;
	private int rfc;

	private int nosi;
	private int loc;
	
	private Set<CKMethodResult> methods;
	private Set<CKMethodResult> visibleMethods;
	private Set<String> fieldNames;
	private int returnQty;
	private int loopQty;
	private int comparisonsQty;
	private int tryCatchQty;
	private int parenthesizedExpsQty;
	private int stringLiteralsQty;
	private int numbersQty;
	private int assignmentsQty;
	private int mathOperationsQty;
	private int variablesQty;
	private int maxNestedBlocks;
	private int anonymousClassesQty;
	private int innerClassesQty;
	private int lambdasQty;
	private int uniqueWordsQty;
	private int numberOfMethods;
	private int numberOfStaticMethods;
	private int numberOfPublicMethods;
	private int numberOfPrivateMethods;
	private int numberOfProtectedMethods;
	private int numberOfDefaultMethods;
	private int numberOfAbstractMethods;
	private int numberOfFinalMethods;
	private int numberOfSynchronizedMethods;
	private int numberOfFields;
	private int numberOfStaticFields;
	private int numberOfPublicFields;
	private int numberOfPrivateFields;
	private int numberOfProtectedFields;
	private int numberOfDefaultFields;
	private int numberOfFinalFields;
	private int numberOfSynchronizedFields;
	private int modifiers;
	private int numberOfLogStatements;

	private float tightClassCohesion;
	private float looseClassCohesion;

	public CKClassResult(String file, String className, String type, int modifiers) {
		this.file = file;
		this.className = className;
		this.type = type;
		this.methods = new HashSet<>();
		this.visibleMethods= new HashSet<>();
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
	
	public String getFile() {
		return file;
	}

	public int getDit() {
		return dit;
	}

	public void setDit(int dit) {
		this.dit = dit;
	}

	public int getNoc(){
		if (this.noc == -1){
			NOCExtras extras = NOCExtras.getInstance();
			this.setNoc(extras.getNocValueByName(this.className));
		}
			
		return this.noc;
	}
	
	public void setNoc(int noc){
		this.noc = noc;
	}
	
	public String getClassName() {
		return className;
	}

	public void setWmc(int cc) {
		this.wmc = cc;
	}
	
	public int getWmc() {
		return wmc;
	}

	public int getCbo() {
		return cbo;
	}

	public void setCbo(int cbo) {
		this.cbo = cbo;
	}
	
	public int getCboModified() {
		if(this.cboModified == -1){
			CouplingExtras extras = CouplingExtras.getInstance();
			this.setCboModified(extras.getValueCBOClass(this.className));
		}
		return cboModified;
	}

	public void setCboModified(int cboModified) {
		this.cboModified = cboModified;
	}

	public int getFanin() {
		
		if(this.fanin == -1){
			CouplingExtras extras = CouplingExtras.getInstance();
			this.setFanin(extras.getValueFanInClass(this.className));
		}
		
		return fanin;
	}
	
	public void setFanin(int fanin) {
		this.fanin = fanin;
	}
	
	public int getFanout() {
		
		if(this.fanout == -1){
			CouplingExtras extras = CouplingExtras.getInstance();
			this.setFanout(extras.getValueFanOutClass(this.className));
		}
		
		return fanout;
	}
	
	public void setFanout(int fanout) {
		this.fanout = fanout;
	}

	public void setLcom(int lcom) {
		this.lcom = lcom;
	}
	public int getLcom() {
		return lcom;
	}

	public void setLcomNormalized(float lcomNormalized) {
		this.lcomNormalized = lcomNormalized;
	}
	
	public float getLcomNormalized() {
		return lcomNormalized;
	}
	
	public void setRfc(int rfc) {
		this.rfc = rfc;
	}
	
	public int getRfc() {
		return rfc;
	}
	
	public int getNosi() {
		return nosi;
	}

	public void setNosi(int nosi) {
		this.nosi = nosi;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}


	@Override
	public String toString() {
		return "CKClassResult [file=" + file + ", className=" + className + "]";
	}

	public void addMethod(CKMethodResult method) {
		this.methods.add(method);
		if(method.getIsVisible()){
			visibleMethods.add(method);
		}
	}

	public Set<CKMethodResult> getMethods() {
		return Collections.unmodifiableSet(methods);
	}

	public Set<CKMethodResult> getVisibleMethods() {
		return Collections.unmodifiableSet(visibleMethods);
	}

	public Optional<CKMethodResult> getMethod(String methodName) {
		return methods.stream().filter(m -> m.getMethodName().equals(methodName)).findFirst();
	}

	public void setFieldNames(Set<String> fieldNames){ this.fieldNames = fieldNames;}

	public Set<String> getFieldNames(){ return fieldNames; }

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public int getReturnQty() {
		return returnQty;
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

	public void setStringLiteralsQty(int stringLiteralsQty) {
		this.stringLiteralsQty = stringLiteralsQty;
	}

	public int getStringLiteralsQty() {
		return stringLiteralsQty;
	}

	public void setNumbersQty(int numbersQty) {
		this.numbersQty = numbersQty;
	}

	public int getNumbersQty() {
		return numbersQty;
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

	public void setVariablesQty(int variablesQty) {
		this.variablesQty = variablesQty;
	}

	public int getVariablesQty() {
		return variablesQty;
	}

	public void setMaxNestedBlocks(int maxNestedBlocks) {
		this.maxNestedBlocks = maxNestedBlocks;
	}

	public int getMaxNestedBlocks() {
		return maxNestedBlocks;
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

	public void setNumberOfMethods(int numberOfMethods) {
		this.numberOfMethods = numberOfMethods;
	}

	public int getNumberOfMethods() {
		return numberOfMethods;
	}

	public void setNumberOfStaticMethods(int numberOfStaticMethods) {
		this.numberOfStaticMethods = numberOfStaticMethods;
	}

	public int getNumberOfStaticMethods() {
		return numberOfStaticMethods;
	}

	public void setNumberOfPublicMethods(int numberOfPublicMethods) {
		this.numberOfPublicMethods = numberOfPublicMethods;
	}

	public int getNumberOfPublicMethods() {
		return numberOfPublicMethods;
	}

	public void setNumberOfPrivateMethods(int numberOfPrivateMethods) {
		this.numberOfPrivateMethods = numberOfPrivateMethods;
	}

	public int getNumberOfPrivateMethods() {
		return numberOfPrivateMethods;
	}

	public void setNumberOfProtectedMethods(int numberOfProtectedMethods) {
		this.numberOfProtectedMethods = numberOfProtectedMethods;
	}

	public int getNumberOfProtectedMethods() {
		return numberOfProtectedMethods;
	}

	public void setNumberOfDefaultMethods(int numberOfDefaultMethods) {
		this.numberOfDefaultMethods = numberOfDefaultMethods;
	}

	public int getNumberOfDefaultMethods() {
		return numberOfDefaultMethods;
	}

	public void setNumberOfAbstractMethods(int numberOfAbstractMethods) {
		this.numberOfAbstractMethods = numberOfAbstractMethods;
	}

	public int getNumberOfAbstractMethods() {
		return numberOfAbstractMethods;
	}

	public void setNumberOfFinalMethods(int numberOfFinalMethods) {
		this.numberOfFinalMethods = numberOfFinalMethods;
	}

	public int getNumberOfFinalMethods() {
		return numberOfFinalMethods;
	}

	public void setNumberOfSynchronizedMethods(int numberOfSynchronizedMethods) {
		this.numberOfSynchronizedMethods = numberOfSynchronizedMethods;
	}

	public int getNumberOfVisibleMethods() { return visibleMethods.size();	}

	public int getNumberOfSynchronizedMethods() {
		return numberOfSynchronizedMethods;
	}

	public void setNumberOfFields(int numberOfFields) {
		this.numberOfFields = numberOfFields;
	}

	public int getNumberOfFields() {
		return numberOfFields;
	}

	public void setNumberOfStaticFields(int numberOfStaticFields) {
		this.numberOfStaticFields = numberOfStaticFields;
	}

	public int getNumberOfStaticFields() {
		return numberOfStaticFields;
	}

	public void setNumberOfPublicFields(int numberOfPublicFields) {
		this.numberOfPublicFields = numberOfPublicFields;
	}

	public int getNumberOfPublicFields() {
		return numberOfPublicFields;
	}

	public void setNumberOfPrivateFields(int numberOfPrivateFields) {
		this.numberOfPrivateFields = numberOfPrivateFields;
	}

	public int getNumberOfPrivateFields() {
		return numberOfPrivateFields;
	}

	public void setNumberOfProtectedFields(int numberOfProtectedFields) {
		this.numberOfProtectedFields = numberOfProtectedFields;
	}

	public int getNumberOfProtectedFields() {
		return numberOfProtectedFields;
	}

	public void setNumberOfDefaultFields(int numberOfDefaultFields) {
		this.numberOfDefaultFields = numberOfDefaultFields;
	}

	public int getNumberOfDefaultFields() {
		return numberOfDefaultFields;
	}

	public void setNumberOfFinalFields(int numberOfFinalFields) {
		this.numberOfFinalFields = numberOfFinalFields;
	}

	public int getNumberOfFinalFields() {
		return numberOfFinalFields;
	}

	public void setNumberOfSynchronizedFields(int numberOfSynchronizedFields) {
		this.numberOfSynchronizedFields = numberOfSynchronizedFields;
	}

	public int getNumberOfSynchronizedFields() {
		return numberOfSynchronizedFields;
	}

	public String getType() {
		return type;
	}

	public void setLogStatementsQty(int numberOfLogStatements) {
		this.numberOfLogStatements = numberOfLogStatements;
	}

	public int getNumberOfLogStatements() {
		return numberOfLogStatements;
	}

	public float getTightClassCohesion() {
		return tightClassCohesion;
	}

	public float getLooseClassCohesion() {
		return looseClassCohesion;
	}

	public void setTightClassCohesion(float tightClassCohesion) {
		this.tightClassCohesion = tightClassCohesion;
	}

	public void setLooseClassCohesion(float looseClassCohesion) {
		this.looseClassCohesion = looseClassCohesion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CKClassResult that = (CKClassResult) o;
		return file.equals(that.file) &&
				className.equals(that.className) &&
				type.equals(that.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, className, type);
	}

}
