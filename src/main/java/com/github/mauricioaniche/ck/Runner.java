package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.util.ResultWriter;

import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException {

		if (args == null || args.length < 1) {
			System.out.println("Usage java -jar ck.jar <path to project> <use Jars=true|false> <max files per partition, 0=automatic selection> <print variables and fields metrics? True|False>");
			System.exit(1);
		}

		String path = args[0];

		// use jars?
		boolean useJars = false;
		if(args.length >= 2)
			useJars = Boolean.parseBoolean(args[1]);

		// number of files per partition?
		int maxAtOnce = 0;
		if(args.length >= 3)
			maxAtOnce = Integer.parseInt(args[2]);

		// variables and field results?
		boolean variablesAndFields = true;
		if(args.length >= 4)
			variablesAndFields = Boolean.parseBoolean(args[3]);


		ResultWriter writer = new ResultWriter("class.csv", "method.csv", "variable.csv", "field.csv", variablesAndFields);
		
		new CK(useJars, maxAtOnce, variablesAndFields).calculate(path, result -> {
			try {
			    writer.printResult(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		
		writer.flushAndClose();
	}
}
