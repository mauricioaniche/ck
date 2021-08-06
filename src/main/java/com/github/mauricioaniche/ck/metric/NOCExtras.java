package com.github.mauricioaniche.ck.metric;

import java.util.HashMap;
import java.util.Map;

import com.github.mauricioaniche.ck.CKClassResult;

public class NOCExtras {

	private Map<String, Integer> toAdd;
	private static NOCExtras instance; 
	
	private NOCExtras(){
		toAdd = new HashMap<>();
	}
	
	public void plusOne(String clazz){
		if(clazz.equals("java.lang.Object"))
			return;
		
		if(!toAdd.containsKey(clazz))
			toAdd.put(clazz, 0);
		
		toAdd.put(clazz, toAdd.get(clazz) + 1);	
	}
	
	public Integer getNocValueByName(String key){
		if(this.toAdd.get(key) != null)
			return this.toAdd.get(key);
		return 0;
	}
	
	public static NOCExtras getInstance(){
		if(instance == null)
			instance = new NOCExtras();
		return instance;
	}
	
}
