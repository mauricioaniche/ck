package com.github.mauricioaniche.ck;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args==null || args.length != 1) {
			System.out.println("Usage java -jar ck.jar <path to project>");
			System.exit(1);
		}
		
		String path = args[0];

		PrintStream classOutput = new PrintStream("class.csv");
		classOutput.println("file,class,type,cbo,wmc,dit,rfc,lcom,nom,nopm,nosm,nof,nopf,nosf,nosi,loc");

		PrintStream methodOutput = new PrintStream("method.csv");
		methodOutput.println("file,class,method,line,cbo,wmc,rfc,loc,returns,variables,parameters");

		PrintStream variableOutput = new PrintStream("variable.csv");
		variableOutput.println("file,class,method,variable,usage");

		new CK().calculate(path, result -> {
			if(result.isError()) return;

			classOutput.println(
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

			for(CKMethodResult method : result.getMethods()) {
				methodOutput.println(
					result.getFile() + "," +
					result.getClassName() + "," +
					method.getMethodName() + "," +
					method.getStartLine() + "," +
					method.getCbo() + "," +
					method.getWmc() + "," +
					method.getRfc() + "," +
					method.getLoc() + "," +
					method.getReturnQty() + "," +
					method.getVariablesQty() + "," +
					method.getParametersQty()
				);

				for(Map.Entry<String,Integer> entry : method.getVariablesUsage().entrySet()) {

					variableOutput.println(
						result.getFile() + "," +
						result.getClassName() + "," +
						method.getMethodName() + "," +
						entry.getKey() + "," + entry.getValue());
				}
			}


		});
		
		classOutput.close();
		methodOutput.close();
		variableOutput.close();
		
	}
}
