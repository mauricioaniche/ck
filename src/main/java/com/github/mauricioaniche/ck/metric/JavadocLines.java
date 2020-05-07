package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class JavadocLines implements CKASTVisitor, MethodLevelMetric {

    // we count how many methods we have visited
    int methodsVisited = 0;
    boolean hasJavadoc = false;

    public void visit(MethodDeclaration node) {
        methodsVisited++;
    }

    public void visit(Javadoc node) {
        /**
         * We only count a javadoc if it is declared in the first
         * method that we visit. Otherwise, we might get a javadoc
         * of an anonymous method.
         */
        if(methodsVisited == 1) {
            this.hasJavadoc = true;
        }
    }

    @Override
    public void setResult(CKMethodResult result) {
        result.setHasJavadoc(hasJavadoc);
    }
}
