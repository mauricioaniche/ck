package br.aniche.ck;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public class Storage extends FileASTRequestor {

	private Map<String, CompilationUnit> cus;
	
	public Storage() {
		this.cus = new HashMap<String, CompilationUnit>();
	}
	
	@Override
	public void acceptAST(String sourceFilePath, 
			CompilationUnit compilationUnit) {

		this.cus.put(sourceFilePath, compilationUnit);	
	}	
	
	public Set<String> keys() {
		return cus.keySet();
	}
	
	public CompilationUnit get(String key) {
		return cus.get(key);
	}
}
