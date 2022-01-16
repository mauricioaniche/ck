package com.github.mauricioaniche.ck.metric;

import java.util.HashMap;
import java.util.Map;

public class NOCExtras {

	private Map<String, Integer> toAdd;
	private static NOCExtras instance; 
	
	private NOCExtras(){
		toAdd = new HashMap<>();
	}

	/**
	 * Resets the <code>NOCExtras</code> instance.
	 * This should ONLY be used in tests so that the state of the instance does not carry over
	 * between different test classes.
	 */
    public static void resetInstance() {
        instance = null;
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
