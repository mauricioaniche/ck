package br.com.aniche.ck;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CKReport {

	private Map<String, CKNumber> results;
	
	public CKReport() {
		this.results = new HashMap<String, CKNumber>();
	}
	
	public void add(CKNumber ck) {
		results.put(ck.getFile(), ck);
	}
	
	public CKNumber get(String name) {
		return results.get(name);
	}
	
	public Collection<CKNumber> all() {
		return results.values();
	}

	public CKNumber getByClassName(String name) {
		List<CKNumber> numbers = all().stream().filter(x -> x.getClassName().equals(name)).collect(Collectors.toList());
		if(numbers.isEmpty()) throw new RuntimeException("didnt find class " + name);
		if(numbers.size()>1) throw new RuntimeException("there are two classes " + name);
		return numbers.get(0);
	}
}
