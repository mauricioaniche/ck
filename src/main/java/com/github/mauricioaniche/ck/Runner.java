package com.github.mauricioaniche.ck;

public class Runner {

	public static void main(String[] args) {
		
		if(args==null || args.length == 0) {
			System.out.println("Usage java -jar ck.jar /path/to/project");
			System.exit(1);
		}
		
		String path = args[0];
		CKReport report = new CK().calculate(path);
		
		System.out.println("file,class,type,cbo,wmc,dit,noc,rfc,lcom,nom");
		for(CKNumber result : report.all()) {
			System.out.println(
					result.getFile() + "," +
					result.getClassName() + "," +
					result.getType() + "," +
					result.getCbo() + "," +
					result.getWmc() + "," +
					result.getDit() + "," +
					result.getNoc() + "," +
					result.getRfc() + "," +
					result.getLcom() + "," +
					result.getNom()
			);
		}

		
	}
}
