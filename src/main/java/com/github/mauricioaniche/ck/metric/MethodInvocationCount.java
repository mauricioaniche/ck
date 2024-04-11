package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.util.HashMap;
import java.util.Map;

public class MethodInvocationCount extends ASTVisitor implements CKASTVisitor, ClassLevelMetric {

    private Map<String, Integer> methodInvocationCounts = new HashMap<>();

    @Override
    public boolean visit(MethodInvocation node) {
        String methodName = node.getName().getIdentifier();
        methodInvocationCounts.merge(methodName, 1, Integer::sum);
        return super.visit(node);
    }

    @Override
    public void setResult(CKClassResult result) {
        result.addMethodInvocation(methodName);
    }
}
