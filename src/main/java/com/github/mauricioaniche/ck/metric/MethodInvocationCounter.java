package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.util.MethodCounter;
import org.eclipse.jdt.core.dom.*;

import java.util.*;

public class MethodInvocationCounter implements CKASTVisitor, ClassLevelMetric {
    private Map<String, MethodCounter.MethodInformation> methodInvocations = new HashMap<>();
    private String currentMethod = null;

    @Override
    public void visit(MethodDeclaration node) {
        // Set the current method context
        this.currentMethod = node.getName().getIdentifier();
        methodInvocations.putIfAbsent(currentMethod, new MethodCounter().new MethodInformation(currentMethod));
    }

    @Override
    public void visit(MethodInvocation node) {
        if (currentMethod != null) {
            // Retrieve or create the MethodInformation for the current method
            MethodCounter.MethodInformation info = methodInvocations.get(currentMethod);
            String methodName = node.getName().getIdentifier();
            Map<String, Integer> counts = info.getMethodInvocations();
            counts.put(methodName, counts.getOrDefault(methodName, 0) + 1);
        }
    }

    @Override
    public void setResult(CKClassResult result) {
        // Process all collected method invocation information into the desired format
        List<MethodCounter.MethodInformation> infos = new ArrayList<>(methodInvocations.values());
        String formattedResult = MethodCounter.formatResult(infos);
        result.setMethodInvocation(formattedResult);
    }

    @Override
    public boolean isVerbose() {
        return true;
    }
}
