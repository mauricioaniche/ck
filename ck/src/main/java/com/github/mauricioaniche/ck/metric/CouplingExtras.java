package com.github.mauricioaniche.ck.metric;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CouplingExtras {

	private Map<String, Set<String>> couplingClassIn;
	private Map<String, Set<String>> couplingClassOut;
	private Map<String, Set<String>> couplingMethodIn;
	private Map<String, Set<String>> couplingMethodOut;
	private static CouplingExtras instance;
	
	private CouplingExtras() {
		this.couplingClassIn = new HashMap<String, Set<String>>();
		this.couplingClassOut = new HashMap<String, Set<String>>();
		this.couplingMethodIn = new HashMap<String, Set<String>>();
		this.couplingMethodOut = new HashMap<String, Set<String>>();
	}
	
	public void addToSetClassIn(String key, String clazz){
		if(this.couplingClassIn.get(key) != null){
			this.couplingClassIn.get(key).add(clazz);
		}else {
			this.couplingClassIn.put(key, new HashSet<>());
			this.couplingClassIn.get(key).add(clazz);
		}
	}
	
	public void addToSetClassOut(String key, String clazz){
		if(this.couplingClassOut.get(key) != null){
			this.couplingClassOut.get(key).add(clazz);
		}else {
			this.couplingClassOut.put(key, new HashSet<>());
			this.couplingClassOut.get(key).add(clazz);
		}
	}
	
	public void addToSetMethodIn(String key, String method){
		if(this.couplingMethodIn.get(key) != null){
			this.couplingMethodIn.get(key).add(method);
		}else {
			this.couplingMethodIn.put(key, new HashSet<>());
			this.couplingMethodIn.get(key).add(method);
		}
	}
	
	public void addToSetMethodOut(String key, String method){
		if(this.couplingMethodOut.get(key) != null){
			this.couplingMethodOut.get(key).add(method);
		}else {
			this.couplingMethodOut.put(key, new HashSet<>());
			this.couplingMethodOut.get(key).add(method);
		}
	}
	
	public int getValueCBOClass(String className){

		return getValueFanInClass(className) + getValueFanOutClass(className);
		
	}
	
	public int getValueCBOMethod(String methodName){

		return getValueFanInMethod(methodName) + getValueFanOutMethod(methodName);
		
	}
	
	public int getValueFanInClass(String className){

		if(this.couplingClassIn.get(className) != null){
			this.couplingClassIn = clean(className, this.couplingClassIn);
			return this.couplingClassIn.get(className).size();
		}
		return 0;
	}
	
	public int getValueFanOutClass(String className){

		if(this.couplingClassOut.get(className) != null){
			this.couplingClassOut = clean(className, this.couplingClassOut);
			return this.couplingClassOut.get(className).size();
		}
		return 0;
	}
	
	public int getValueFanInMethod(String methodName){

		if(this.couplingMethodIn.get(methodName) != null)
			return this.couplingMethodIn.get(methodName).size();
		
		return 0;
	}
	
	public int getValueFanOutMethod(String methodName){

		if(this.couplingMethodOut.get(methodName) != null)
			return this.couplingMethodOut.get(methodName).size();
		
		return 0;
	}
	
	private Map<String, Set<String>> clean(String componentName, Map<String, Set<String>> coupling) {
		Set<String> singleQualifiedTypes = coupling.get(componentName).stream().filter(x -> !x.contains(".")).collect(Collectors.toSet());

		for(String singleQualifiedType : singleQualifiedTypes) {
			long count = coupling.get(componentName).stream().filter(x -> x.endsWith("." + singleQualifiedType)).count();

			boolean theSameFullyQualifiedTypeExists = count > 0;
			if(theSameFullyQualifiedTypeExists)
				coupling.get(componentName).remove(singleQualifiedType);
		}
		
		return coupling;
	}
	
	public static CouplingExtras getInstance(){
		if(instance == null){
			instance = new CouplingExtras();
		}
		return instance;
	}
	
}
