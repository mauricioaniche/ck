package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AssertResult {

    static Set<Method> classGetters = Arrays.stream(CKClassResult.class.getMethods())
            .filter(m -> m.getName().startsWith("get"))
            .filter(m -> !m.getName().equals("getModifiers")) // it can be -1 if it's anonymous
            .filter(m -> m.getParameterCount() == 0)
            .collect(Collectors.toSet());

    static Set<Method> methodGetters = Arrays.stream(CKMethodResult.class.getMethods())
            .filter(m -> m.getName().startsWith("get"))
            .filter(m -> !m.getName().equals("getModifiers"))
            .filter(m -> m.getParameterCount() == 0)
            .collect(Collectors.toSet());


    public static void assertResultNotNull(CKClassResult ckClassResult) {
        try {
            for (Method method : classGetters) {
                Object result = method.invoke(ckClassResult);
                Assertions.assertNotNull(result, method.getName() + " is null");

                // integer >= 0
                if (method.getReturnType().equals(int.class)) {
                    int intResult = (int) result;
                    Assertions.assertTrue(intResult >= 0, method.getName() + " < 0");
                }
            }

            Set<CKMethodResult> ckMethods = ckClassResult.getMethods();
            // no nulls in getters of the class
            for (CKMethodResult ckMethodResult : ckMethods) {
                for (Method method : methodGetters) {
                    Object result = method.invoke(ckMethodResult);
                    Assertions.assertNotNull(result, method.getName() + " is null");

                    // integer >= 0
                    if (method.getReturnType().equals(int.class)) {
                        int intResult = (int) result;
                        Assertions.assertTrue(intResult >= 0, method.getName() + " < 0");
                    }

                }
            }
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
