package com.github.mauricioaniche.ck;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args==null || args.length != 1) {
			System.out.println("Usage java -jar ck.jar <path to project>");
			System.exit(1);
		}
		
		String path = args[0];

		PrintStream classOutput = new PrintStream("class.csv");
		classOutput.println("file,class,type,cbo,wmc,dit,rfc,lcom,nom,nopm,nosm,nof,nopf,nosf,nosi,loc,returnQty,loopQty,comparisonsQty," +
				"tryCatchQty,parenthesizedExpsQty,stringLiteralsQty,numbersQty,assignmentsQty,mathOperationsQty,variablesQty," +
				"maxNestedBlocks,anonymousClassesQty,subClassesQty,lambdasQty,uniqueWordsQty");

		PrintStream methodOutput = new PrintStream("method.csv");
		methodOutput.println("file,class,method,line,cbo,wmc,rfc,loc,returns,variables,parameters,startLine," +
				"loopQty,comparisonsQty,tryCatchQty,parenthesizedExpsQty,stringLiteralsQty,numbersQty,assignmentsQty," +
				"mathOperationsQty,maxNestedBlocks,anonymousClassesQty,subClassesQty,lambdasQty,uniqueWordsQty");

		PrintStream variableOutput = new PrintStream("variable.csv");
		variableOutput.println("file,class,method,variable,usage");

		PrintStream fieldOutput = new PrintStream("field.csv");
		fieldOutput.println("file,class,method,variable,usage");

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
				result.getLoc() + "," +
				result.getReturnQty() + "," +
				result.getLoopQty() + "," +
				result.getComparisonsQty() + "," +
				result.getTryCatchQty() + "," +
				result.getParenthesizedExpsQty() + "," +
				result.getStringLiteralsQty() + "," +
				result.getNumbersQty() + "," +
				result.getAssignmentsQty() + "," +
				result.getMathOperationsQty() + "," +
				result.getMathOperationsQty() + "," +
				result.getVariablesQty() + "," +
				result.getMaxNestedBlocks() + "," +
				result.getAnonymousClassesQty() + "," +
				result.getSubClassesQty() + "," +
				result.getLambdasQty() + "," +
				result.getUniqueWordsQty()
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
					method.getParametersQty()  + "," +
					method.getStartLine() + "," +
					method.getLoopQty() + "," +
					method.getComparisonsQty() + "," +
					method.getTryCatchQty() + "," +
					method.getParenthesizedExpsQty() + "," +
					method.getStringLiteralsQty() + "," +
					method.getNumbersQty() + "," +
					method.getAssignmentsQty() + "," +
					method.getMathOperationsQty() + "," +
					method.getMaxNestedBlocks() + "," +
					method.getAnonymousClassesQty() + "," +
					method.getSubClassesQty() + "," +
					method.getLambdasQty() + "," +
					method.getUniqueWordsQty()
				);

				for(Map.Entry<String,Integer> entry : method.getVariablesUsage().entrySet()) {

					variableOutput.println(
						result.getFile() + "," +
						result.getClassName() + "," +
						method.getMethodName() + "," +
						entry.getKey() + "," + entry.getValue());
				}

				for(Map.Entry<String,Integer> entry : method.getFieldUsage().entrySet()) {

					fieldOutput.println(
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
		fieldOutput.close();
		
	}
}
