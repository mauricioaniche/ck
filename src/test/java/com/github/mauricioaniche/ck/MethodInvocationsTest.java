package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.apache.commons.lang.ArrayUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MethodInvocationsTest extends BaseTest {
    private static Map<String, CKClassResult> report;

    @BeforeAll
    public static void setUp() {
        report = run(fixturesDir() + "/MethodInvocation");
    }

    //Test method invocations
    private void testMethodInvocation(Map<String, Set<String>> methodInvocations, Set<CKMethodResult> methods){
        for (CKMethodResult methodResult : methods){
            Assert.assertEquals(
                    methodInvocations.get(methodResult.getQualifiedMethodName()),
                    methodResult.getMethodInvocations());
        }
    }

    @Test
    public void methodInvocations1() {
        String qualifier = "MethodInvocation.Simple1.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m2/1[int]", "MethodInvocation.Simple2.x/0"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + "m3/1[java.lang.String]"));
        methodInvocations.put(qualifier + "m3/1[java.lang.String]",  Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.Simple1");
        testMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void methodInvocations2() {
        String qualifier = "MethodInvocation.Simple2.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "x/0", Sets.newHashSet(qualifier + "z/0", "java.io.PrintStream.println/1[int]"));
        methodInvocations.put(qualifier + "z/0", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.Simple2");
        testMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void methodInvocations3() {
        String qualifier = "MethodInvocation.GO.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m2/1[int]", "MethodInvocation.GO2.magic/0"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));
        methodInvocations.put(qualifier + "m3/0",  Sets.newHashSet("xyz/0"));

        CKClassResult a = report.get("MethodInvocation.GO");
        testMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void methodInvocations4() {
        String qualifier = "MethodInvocation.GO2.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "magic/0", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.GO2");
        testMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void methodInvocations5() {
        String qualifier = "MethodInvocation.GO3.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "x/0", Sets.newHashSet(qualifier + "x/0", "MethodInvocation.GO2.magic/0"));

        CKClassResult a = report.get("MethodInvocation.GO3");
        testMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void methodInvocations6() {
        String qualifier = "MethodInvocation.Complex1.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m1/0", qualifier + "m2/1[int]", "MethodInvocation.Simple2.x/0"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + "m3/1[java.lang.String]", "MethodInvocation.GO2.magic/0"));
        methodInvocations.put(qualifier + "m3/1[java.lang.String]",  Sets.newHashSet("java.io.PrintStream.println/1[int]", qualifier + "m4/2[java.lang.String,int]"));
        methodInvocations.put(qualifier + "m4/2[java.lang.String,int]",  Sets.newHashSet("MethodInvocation.Simple2.x/0", qualifier + "m2/1[int]"));

        CKClassResult a = report.get("MethodInvocation.Complex1");
        testMethodInvocation(methodInvocations, a.getMethods());
    }

    //Test local method invocations
    private void testLocalMethodInvocation(Map<String, Set<String>> methodInvocations, Set<CKMethodResult> methods){
        for (CKMethodResult methodResult : methods){
            Assert.assertEquals(
                    methodInvocations.get(methodResult.getQualifiedMethodName()),
                    methodResult.getMethodInvocationsLocal());
        }
    }

    @Test
    public void localMethodInvocations1() {
        String qualifier = "MethodInvocation.Simple1.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m2/1[int]"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + "m3/1[java.lang.String]"));
        methodInvocations.put(qualifier + "m3/1[java.lang.String]",  Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.Simple1");
        testLocalMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void localMethodInvocations2() {
        String qualifier = "MethodInvocation.Simple2.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "x/0", Sets.newHashSet(qualifier + "z/0"));
        methodInvocations.put(qualifier + "z/0", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.Simple2");
        testLocalMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void localMethodInvocations3() {
        String qualifier = "MethodInvocation.GO.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m2/1[int]"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));
        methodInvocations.put(qualifier + "m3/0",  Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.GO");
        testLocalMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void localMethodInvocations4() {
        String qualifier = "MethodInvocation.GO2.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "magic/0", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.GO2");
        testLocalMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void localMethodInvocations5() {
        String qualifier = "MethodInvocation.GO3.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "x/0", Sets.newHashSet(qualifier + "x/0"));

        CKClassResult a = report.get("MethodInvocation.GO3");
        testLocalMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void localMethodInvocations6() {
        String qualifier = "MethodInvocation.Complex1.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m1/0", qualifier + "m2/1[int]"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + "m3/1[java.lang.String]"));
        methodInvocations.put(qualifier + "m3/1[java.lang.String]",  Sets.newHashSet(qualifier + "m4/2[java.lang.String,int]"));
        methodInvocations.put(qualifier + "m4/2[java.lang.String,int]",  Sets.newHashSet(qualifier + "m2/1[int]"));

        CKClassResult a = report.get("MethodInvocation.Complex1");
        testLocalMethodInvocation(methodInvocations, a.getMethods());
    }

    //Test indirect local method invocations
    private void testLocalIndirectMethodInvocation(Map<String, Set<String>> methodInvocations, Set<CKMethodResult> methods){
        for (CKMethodResult methodResult : methods){
            Assert.assertEquals(
                    methodInvocations.get(methodResult.getQualifiedMethodName()),
                    methodResult.getMethodInvocationsIndirectLocal());
        }
    }

    @Test
    public void localIndirectMethodInvocations1() {
        String qualifier = "MethodInvocation.Simple1.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m3/1[java.lang.String]"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));
        methodInvocations.put(qualifier + "m3/1[java.lang.String]", Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY));

        CKClassResult a = report.get("MethodInvocation.Simple1");
        testLocalIndirectMethodInvocation(methodInvocations, a.getMethods());
    }

    @Test
    public void localIndirectMethodInvocations2() {
        String qualifier = "MethodInvocation.Complex1.";
        Map<String, Set<String>> methodInvocations = new HashMap<>();
        methodInvocations.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m3/1[java.lang.String]", qualifier + "m4/2[java.lang.String,int]"));
        methodInvocations.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + "m4/2[java.lang.String,int]"));
        methodInvocations.put(qualifier + "m3/1[java.lang.String]",  Sets.newHashSet(qualifier + "m2/1[int]"));
        methodInvocations.put(qualifier + "m4/2[java.lang.String,int]",  Sets.newHashSet(qualifier + "m3/1[java.lang.String]"));

        CKClassResult a = report.get("MethodInvocation.Complex1");
        testLocalIndirectMethodInvocation(methodInvocations, a.getMethods());
    }
}