package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.util.ResultWriter;

import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException {

		if (args == null || args.length < 1) {
			System.out.println("Usage java -jar ck.jar <path to project> <use Jars=true|false> <max files per partition, 0=automatic selection>");
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

		ResultWriter writer = new ResultWriter("class.csv", "method.csv", "variable.csv", "field.csv");
		
		new CK(useJars, maxAtOnce).calculate(path, result -> {
			try {
			    writer.printResult(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		
		writer.flushAndClose();
	}
}
