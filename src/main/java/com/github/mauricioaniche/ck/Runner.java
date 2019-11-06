package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.util.ResultWriter;

import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException {

		if (args == null || args.length < 1) {
			System.out.println("Usage java -jar ck.jar <path to project> <use Jars=true|false>");
			System.exit(1);
		}

		String path = args[0];
		boolean useJars = false;
		if(args.length == 2)
			useJars = Boolean.parseBoolean(args[1]);
		
		ResultWriter writer = new ResultWriter("class.csv", "method.csv", "variable.csv", "field.csv");
		
		new CK().calculate(path, useJars, result -> {
			try {
			    writer.printResult(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		
		writer.flushAndClose();
	}
}
