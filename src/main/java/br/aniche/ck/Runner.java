package br.aniche.ck;

public class Runner {

	public static void main(String[] args) {
		
//		String path = args[0];
		String path = "/Users/mauricioaniche/workspace/gnarus/src/main";
		
		CKReport report = new CK().calculate(path);
		
		System.out.println("class,cbo,wmc,dit,noc,rfc,lcom");
		for(CKNumber result : report.all()) {
			System.out.println(
					result.getClassName() + "," +
					result.getCbo() + "," +
					result.getWmc() + "," +
					result.getDit() + "," +
					result.getNoc() + "," +
					result.getRfc() + "," +
					result.getLcom()
			);
		}

		
	}
}
