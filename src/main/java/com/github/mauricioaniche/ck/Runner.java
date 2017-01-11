package com.github.mauricioaniche.ck;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args==null || args.length < 2) {
			System.out.println("Usage java -jar ck.jar <path to project> <path to csv>");
			System.exit(1);
		}
		
		String path = args[0];
		String csvPath = args[1];
		
		CKReport report = new CK().calculate(path);
		
		PrintStream ps = new PrintStream(csvPath);
		ps.println("file,class,type,cbo,wmc,dit,noc,rfc,lcom,nom,nopm,nosm,nof,nopf,nosf,nosi,loc");
		for(CKNumber result : report.all()) {
			if(result.isError()) continue;
			
			ps.println(
				result.getFile() + "," +
				result.getClassName() + "," +
				result.getType() + "," +
				result.getCbo() + "," +
				result.getWmc() + "," +
				result.getDit() + "," +
				result.getNoc() + "," +
				result.getRfc() + "," +
				result.getLcom() + "," +
				result.getNom() + "," +
				result.getNopm() + "," + 
				result.getNosm() + "," +
				result.getNof() + "," +
				result.getNopf() + "," + 
				result.getNosf() + "," +
				result.getNosi() + "," +
				result.getLoc()
			);
		}

		ps.close();
		
	}
}
