package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class NumberOfLogStatements implements CKASTVisitor, MethodLevelMetric, ClassLevelMetric {

    private int qty = 0;

    /**
     * Checks whether an expression represents a log statement based on REGEX.
     * <p>
     * Pattern based on log4j API. It also supports builder pattern (see http://logging.apache.org/log4j/2.x/manual/logbuilder.html)
     * <p>
     * Limitations:
     * 1. It does not check for EventLogs as in http://logging.apache.org/log4j/2.x/manual/eventlogging.html
     * 2. It does not support custom log levels as in http://logging.apache.org/log4j/2.x/manual/customloglevels.html
     *
     * @param line The string representation of the given statement
     * @return <code>true</code> if the informed line matches a log statement
     */
    public static boolean isLogStatement(String line) {
        line = line.toLowerCase().trim();
        return line.matches(".*\\.(at)?(info|warn|debug|error|trace)\\(.*");
    }

    @Override
    public void visit(MethodInvocation node) {
        ASTNode parentNode = node.getParent();
        if (parentNode instanceof ExpressionStatement) {
            ExpressionStatement expr = (ExpressionStatement) parentNode;
            String rawExpr = expr.toString();
            if (NumberOfLogStatements.isLogStatement(rawExpr)) {
                qty++;
            }
        }
    }

    @Override
    public void setResult(CKMethodResult result) {
        result.setLogStatementsQty(qty);
    }

    @Override
    public void setResult(CKClassResult result) {
        result.setLogStatementsQty(qty);

    }
}
