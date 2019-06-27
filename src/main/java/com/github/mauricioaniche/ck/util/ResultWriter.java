package com.github.mauricioaniche.ck.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;

public class ResultWriter {

    private static final String[] CLASS_HEADER = { "file", "class", "type", "cbo", "wmc", "dit", "rfc", "lcom",
            "totalMethods", "staticMethods", "publicMethods", "privateMethods", "protectedMethods", "defaultMethods",
            "abstractMethods", "finalMethods", "synchronizedMethods", "totalFields", "staticFields", "publicFields",
            "privateFields", "protectedFields", "defaultFields", "finalFields", "synchronizedFields", "nosi", "loc",
            "returnQty", "loopQty", "comparisonsQty", "tryCatchQty", "parenthesizedExpsQty", "stringLiteralsQty",
            "numbersQty", "assignmentsQty", "mathOperationsQty", "variablesQty", "maxNestedBlocks",
            "anonymousClassesQty", "subClassesQty", "lambdasQty", "uniqueWordsQty" };
    private static final String[] METHOD_HEADER = { "file", "class", "method", "line", "cbo", "wmc", "rfc", "loc",
            "returns", "variables", "parameters", "startLine", "loopQty", "comparisonsQty", "tryCatchQty",
            "parenthesizedExpsQty", "stringLiteralsQty", "numbersQty", "assignmentsQty", "mathOperationsQty",
            "maxNestedBlocks", "anonymousClassesQty", "subClassesQty", "lambdasQty", "uniqueWordsQty" };
    private static final String[] VAR_FIELD_HEADER = { "file", "class", "method", "variable", "usage" };

    private CSVPrinter classPrinter;
    private CSVPrinter methodPrinter;
    private CSVPrinter variablePrinter;
    private CSVPrinter fieldPrinter;

    /**
     * Initialise a new ResultWriter that writes to the specified files. Begins by
     * writing CSV headers to each file.
     * 
     * @param classFile    Output file for class metrics
     * @param methodFile   Output file for method metrics
     * @param variableFile Output file for variable metrics
     * @param fieldFile    Output file for field metrics
     * @throws IOException If headers cannot be written
     */
    public ResultWriter(String classFile, String methodFile, String variableFile, String fieldFile) throws IOException {
        FileWriter classOut = new FileWriter(classFile);
        this.classPrinter = new CSVPrinter(classOut, CSVFormat.DEFAULT.withHeader(CLASS_HEADER));
        FileWriter methodOut = new FileWriter(methodFile);
        this.methodPrinter = new CSVPrinter(methodOut, CSVFormat.DEFAULT.withHeader(METHOD_HEADER));
        FileWriter variableOut = new FileWriter(variableFile);
        this.variablePrinter = new CSVPrinter(variableOut, CSVFormat.DEFAULT.withHeader(VAR_FIELD_HEADER));
        FileWriter fieldOut = new FileWriter(fieldFile);
        this.fieldPrinter = new CSVPrinter(fieldOut, CSVFormat.DEFAULT.withHeader(VAR_FIELD_HEADER));
    }

    /**
     * Print results for a single class and its methods and fields to the
     * appropriate CSVPrinters.
     * 
     * @param result The CKClassResult
     * @throws IOException If output files cannot be written to
     */
    public void printResult(CKClassResult result) throws IOException {
        if (result.isError()) {
            return;
        }

        this.classPrinter.printRecord(result.getFile(), result.getClassName(), result.getType(), result.getCbo(),
                result.getWmc(), result.getDit(), result.getRfc(), result.getLcom(), result.getNumberOfMethods(),
                result.getNumberOfStaticMethods(), result.getNumberOfPublicMethods(),
                result.getNumberOfPrivateMethods(), result.getNumberOfProtectedMethods(),
                result.getNumberOfDefaultMethods(), result.getNumberOfAbstractMethods(),
                result.getNumberOfFinalMethods(), result.getNumberOfSynchronizedMethods(), result.getNumberOfFields(),
                result.getNumberOfStaticFields(), result.getNumberOfPublicFields(), result.getNumberOfPrivateFields(),
                result.getNumberOfProtectedFields(), result.getNumberOfDefaultFields(), result.getNumberOfFinalFields(),
                result.getNumberOfSynchronizedFields(), result.getNosi(), result.getLoc(), result.getReturnQty(),
                result.getLoopQty(), result.getComparisonsQty(), result.getTryCatchQty(),
                result.getParenthesizedExpsQty(), result.getStringLiteralsQty(), result.getNumbersQty(),
                result.getAssignmentsQty(), result.getMathOperationsQty(), result.getVariablesQty(),
                result.getMaxNestedBlocks(), result.getAnonymousClassesQty(), result.getSubClassesQty(),
                result.getLambdasQty(), result.getUniqueWordsQty());

        for (CKMethodResult method : result.getMethods()) {
            this.methodPrinter.printRecord(result.getFile(), result.getClassName(), method.getMethodName(),
                    method.getStartLine(), method.getCbo(), method.getWmc(), method.getRfc(), method.getLoc(),
                    method.getReturnQty(), method.getVariablesQty(), method.getParametersQty(), method.getStartLine(),
                    method.getLoopQty(), method.getComparisonsQty(), method.getTryCatchQty(),
                    method.getParenthesizedExpsQty(), method.getStringLiteralsQty(), method.getNumbersQty(),
                    method.getAssignmentsQty(), method.getMathOperationsQty(), method.getMaxNestedBlocks(),
                    method.getAnonymousClassesQty(), method.getSubClassesQty(), method.getLambdasQty(),
                    method.getUniqueWordsQty());

            for (Map.Entry<String, Integer> entry : method.getVariablesUsage().entrySet()) {
                this.variablePrinter.printRecord(result.getFile(), result.getClassName(), method.getMethodName(),
                        entry.getKey(), entry.getValue());
            }

            for (Map.Entry<String, Integer> entry : method.getFieldUsage().entrySet()) {
                this.fieldPrinter.printRecord(result.getFile(), result.getClassName(), method.getMethodName(),
                        entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Flush and close resources that were opened to write results. This method
     * should be called after all CKClassResults have been calculated and printed.
     * 
     * @throws IOException If the resources cannot be closed
     */
    public void flushAndClose() throws IOException {
        this.classPrinter.flush();
        this.classPrinter.close();
        this.methodPrinter.flush();
        this.methodPrinter.close();
        this.variablePrinter.flush();
        this.variablePrinter.close();
        this.fieldPrinter.flush();
        this.fieldPrinter.close();
    }
}
