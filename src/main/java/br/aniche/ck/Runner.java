package br.aniche.ck;

public class Runner {

	public static void main(String[] args) {
		
//		String path = args[0];
		String path = "/Users/mauricioaniche/workspace/metricminer2/";
		
		CKReport report = new CK().calculate(path);
		
		for(CKNumber result : report.all()) {
			System.out.println(result.getFile());
		}

		
	}
}
