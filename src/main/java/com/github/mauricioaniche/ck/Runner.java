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

		PrintStream ps = new PrintStream(csvPath);
		ps.println("file,class,type,cbo,wmc,dit,rfc,lcom,nom,nopm,nosm,nof,nopf,nosf,nosi,loc");

		new CK().calculate(path, result -> {
			if(result.isError()) return;

			ps.println(
				result.getFile() + "," +
				result.getClassName() + "," +
				result.getType() + "," +
				result.getCbo() + "," +
				result.getWmc() + "," +
				result.getDit() + "," +
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
		});
		
		ps.close();
		
	}
}
