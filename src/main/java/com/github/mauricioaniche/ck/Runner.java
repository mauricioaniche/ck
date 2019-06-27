package com.github.mauricioaniche.ck;

import java.io.IOException;

import com.github.mauricioaniche.ck.util.ResultWriter;

public class Runner {

	public static void main(String[] args) throws IOException {

		if (args == null || args.length != 1) {
			System.out.println("Usage java -jar ck.jar <path to project>");
			System.exit(1);
		}

		String path = args[0];
		
		ResultWriter writer = new ResultWriter("class.csv", "method.csv", "variable.csv", "field.csv");
		
		new CK().calculate(path, result -> {
			try {
			    writer.printResult(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		
		writer.flushAndClose();
	}
}
