package br.aniche.ck;

import java.util.Set;


public class Runner {

	public static void main(String[] args) {
		
//		String path = args[0];
		String path = "/Users/mauricioaniche/workspace/metricminer2/";
		
		Set<CalculatedCK> results = new CK().parseAll(path);
		
		for(CalculatedCK result : results) {
			System.out.println(result.getFile());
		}

		
	}
}
