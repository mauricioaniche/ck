package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

public class NumberOfLogStatements implements CKASTVisitor, MethodLevelMetric, ClassLevelMetric {

    private int qty = 0;

    @Override
    public void visit(MethodInvocation node) {
        ASTNode parentNode = node.getParent();
        if (parentNode instanceof ExpressionStatement) {
            ExpressionStatement expr = (ExpressionStatement) parentNode;
            String rawExpr = expr.toString();
            if (isLogStatement(rawExpr)) {
                qty++;
            }
        }
    }

    private boolean isLogStatement(String line) {
        line = line.toLowerCase().trim();
        return (line.matches(".*\\.(info|warn|debug|error)\\(.*")
                || line.matches(".*log(ger)?\\..*"));
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
