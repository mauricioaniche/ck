package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import java.util.*;
import java.util.stream.Collectors;

//we ignore invocations in the super class, because they are always outside the current class and can never return
@RunAfter(metrics={RFC.class, MethodLevelFieldUsageCount.class})
public class MethodInvocationsLocal implements CKASTVisitor, ClassLevelMetric {
    //Recursively extract all method invocations starting with the given method
    //Explored contains all previously explored invocations
    //Invocations contains all direct method invocations of interest
    //The algorithm is a depth first search
    private Map<String, Set<String>> invocations(String invokedMethod, Map<String, Set<String>> explored, HashMap<String, Set<String>> invocations){
        //only explore local method invocations that were not previously explored
        Set<String> exploredKeys = explored.keySet();
        Set<String> nextInvocations = invocations.get(invokedMethod).stream()
                .filter(invoked -> !exploredKeys.contains(invoked) && !invoked.equals(invokedMethod))
                .collect(Collectors.toSet());
        if(nextInvocations.size() > 0){
            explored.put(invokedMethod, nextInvocations);

            for (String nextInvocation : nextInvocations){
                explored = invocations(nextInvocation, explored, invocations);
            }
        }

        //Stops when all invocations are explored: there are no more invocations to be explored
        return explored;
    }

    //Generate an indirect method invocations map
    //Method contains all methods of interest
    //Invocations contains all indirect method invocations of interest with the calling method
    private HashMap<String, Map<String, Set<String>>> invocationsIndirect(Set<CKMethodResult> methods, HashMap<String, Set<String>> methodInvocationsLocal){
        HashMap<String, Map<String, Set<String>>> methodInvocationsIndirectLocal = new HashMap<>();

        //extract all indirect local invocations for all methods in the current class
        for (CKMethodResult method : methods){
            //extract all local invocations for the current method
            Map<String, Set<String>> localInvocations =  invocations(method.getQualifiedMethodName(), new HashMap(), methodInvocationsLocal);
            methodInvocationsIndirectLocal.put(method.getQualifiedMethodName(), localInvocations);
        }
        return methodInvocationsIndirectLocal;
    }

    //Extract all local(inner-class) method invocations and save them in a HashMap
    private HashMap<String, Set<String>> extractLocalInvocations(Set<CKMethodResult> methods){
        HashMap<String, Set<String>> methodInvocationsLocal = new HashMap<>();

        Set<String> methodNames = methods.stream().map(CKMethodResult::getQualifiedMethodName).collect(Collectors.toSet());
        for (CKMethodResult method : methods){
            Set<String> invokedLocal =  method.getMethodInvocations().stream()
                    .filter(methodNames::contains)
                    .collect(Collectors.toSet());
            methodInvocationsLocal.put(method.getQualifiedMethodName(), invokedLocal);
        }
        return methodInvocationsLocal;
    }

    public void setResult(CKClassResult result) {
        //extract all direct local invocations for all methods in the current class
        Set<CKMethodResult> methods = result.getMethods();
        HashMap<String, Set<String>> methodInvocationsLocal = extractLocalInvocations(methods);
        for (CKMethodResult method : methods){
            method.setMethodInvocationLocal(methodInvocationsLocal.get(method.getQualifiedMethodName()));
        }

        HashMap<String, Map<String, Set<String>>> methodInvocationsIndirectLocal = invocationsIndirect(methods, methodInvocationsLocal);
        for (CKMethodResult method : methods){
            method.setMethodInvocationsIndirectLocal(methodInvocationsIndirectLocal.get(method.getQualifiedMethodName()));
        }
    }
}