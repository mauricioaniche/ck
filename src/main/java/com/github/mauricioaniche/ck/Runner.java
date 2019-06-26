package com.github.mauricioaniche.ck;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Runner {

	public static void main(String[] args) throws IOException {

		if (args == null || args.length != 1) {
			System.out.println("Usage java -jar ck.jar <path to project>");
			System.exit(1);
		}

		String path = args[0];

		String[] classHeader = { "file", "class", "type", "cbo", "wmc", "dit", "rfc", "lcom", "totalMethods",
				"staticMethods", "publicMethods", "privateMethods", "protectedMethods", "defaultMethods",
				"abstractMethods", "finalMethods", "synchronizedMethods", "totalFields", "staticFields", "publicFields",
				"privateFields", "protectedFields", "defaultFields", "finalFields", "synchronizedFields", "nosi", "loc",
				"returnQty", "loopQty", "comparisonsQty", "tryCatchQty", "parenthesizedExpsQty", "stringLiteralsQty",
				"numbersQty", "assignmentsQty", "mathOperationsQty", "variablesQty", "maxNestedBlocks",
				"anonymousClassesQty", "subClassesQty", "lambdasQty", "uniqueWordsQty" };
		String[] methodHeader = { "file", "class", "method", "line", "cbo", "wmc", "rfc", "loc", "returns", "variables",
				"parameters", "startLine", "loopQty", "comparisonsQty", "tryCatchQty", "parenthesizedExpsQty",
				"stringLiteralsQty", "numbersQty", "assignmentsQty", "mathOperationsQty", "maxNestedBlocks",
				"anonymousClassesQty", "subClassesQty", "lambdasQty", "uniqueWordsQty" };
		String[] varFieldHeader = { "file", "class", "method", "variable", "usage" };
		
		FileWriter classOut = new FileWriter("class.csv");
		CSVPrinter classPrinter = new CSVPrinter(classOut, CSVFormat.DEFAULT.withHeader(classHeader));
		FileWriter methodOut = new FileWriter("method.csv");
		CSVPrinter methodPrinter = new CSVPrinter(methodOut, CSVFormat.DEFAULT.withHeader(methodHeader));
		FileWriter variableOut = new FileWriter("variable.csv");
		CSVPrinter variablePrinter = new CSVPrinter(variableOut, CSVFormat.DEFAULT.withHeader(varFieldHeader));
		FileWriter fieldOut = new FileWriter("field.csv");
		CSVPrinter fieldPrinter = new CSVPrinter(fieldOut, CSVFormat.DEFAULT.withHeader(varFieldHeader));
		
		new CK().calculate(path, result -> {
			try {
				classPrinter.printRecord(
						result.getFile(),
						result.getClassName(),
						result.getType(),
						result.getCbo(),
						result.getWmc(),
						result.getDit(),
						result.getRfc(),
						result.getLcom(),
						result.getNumberOfMethods(),
						result.getNumberOfStaticMethods(),
						result.getNumberOfPublicMethods(),
						result.getNumberOfPrivateMethods(),
						result.getNumberOfProtectedMethods(),
						result.getNumberOfDefaultMethods(),
						result.getNumberOfAbstractMethods(),
						result.getNumberOfFinalMethods(),
						result.getNumberOfSynchronizedMethods(),
						result.getNumberOfFields(),
						result.getNumberOfStaticFields(),
						result.getNumberOfPublicFields(),
						result.getNumberOfPrivateFields(),
						result.getNumberOfProtectedFields(),
						result.getNumberOfDefaultFields(),
						result.getNumberOfFinalFields(),
						result.getNumberOfSynchronizedFields(),
						result.getNosi(),
						result.getLoc(),
						result.getReturnQty(),
						result.getLoopQty(),
						result.getComparisonsQty(),
						result.getTryCatchQty(),
						result.getParenthesizedExpsQty(),
						result.getStringLiteralsQty(),
						result.getNumbersQty(),
						result.getAssignmentsQty(),
						result.getMathOperationsQty(),
						result.getVariablesQty(),
						result.getMaxNestedBlocks(),
						result.getAnonymousClassesQty(),
						result.getSubClassesQty(),
						result.getLambdasQty(),
						result.getUniqueWordsQty());

				for (CKMethodResult method : result.getMethods()) {
					methodPrinter.printRecord(
							result.getFile(), 
							result.getClassName(), 
							method.getMethodName(),
							method.getStartLine(),
							method.getCbo(),
							method.getWmc(),
							method.getRfc(),
							method.getLoc(),
							method.getReturnQty(),
							method.getVariablesQty(),
							method.getParametersQty(),
							method.getStartLine(),
							method.getLoopQty(),
							method.getComparisonsQty(),
							method.getTryCatchQty(),
							method.getParenthesizedExpsQty(),
							method.getStringLiteralsQty(),
							method.getNumbersQty(),
							method.getAssignmentsQty(),
							method.getMathOperationsQty(),
							method.getMaxNestedBlocks(),
							method.getAnonymousClassesQty(),
							method.getSubClassesQty(),
							method.getLambdasQty(),
							method.getUniqueWordsQty());

					for (Map.Entry<String, Integer> entry : method.getVariablesUsage().entrySet()) {
						variablePrinter.printRecord(result.getFile(), result.getClassName(), method.getMethodName(),
								entry.getKey(), entry.getValue());
					}

					for (Map.Entry<String, Integer> entry : method.getFieldUsage().entrySet()) {
						fieldPrinter.printRecord(result.getFile(), result.getClassName(), method.getMethodName(),
								entry.getKey(), entry.getValue());
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		
		classPrinter.flush();
		classPrinter.close();
		methodPrinter.flush();
		methodPrinter.close();
		variablePrinter.flush();
		variablePrinter.close();
		fieldPrinter.flush();
		fieldPrinter.close();
	}
}
