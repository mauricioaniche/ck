package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.util.MethodCounter;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MethodInvocationCounter implements CKASTVisitor, ClassLevelMetric {
    private String methodList;

    @Override
    public void visit(MethodInvocation node) {
        if (methodList == null) {
            this.methodList = "";
        }

        String methodName = node.toString();

        if (methodName.contains("(")) {
            methodName = methodName.substring(0, methodName.indexOf("("));
        }

        this.methodList += methodName + ";";
    }

    @Override
    public void setResult(CKClassResult result) {
        String methodList = MethodCounter.formatResult(MethodCounter.sort(MethodCounter.count(this.methodList)));
        result.setMethodInvocation(methodList);
    }
}
