package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.apache.commons.lang.ArrayUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class MethodInvocationsTest extends BaseTest {
    private static Map<String, CKClassResult> report;
    private String qualifier = "MethodInvocation.Complex1.";

    @BeforeAll
    public static void setUp() {
        report = run(fixturesDir() + "/MethodInvocation");
    }

    //Test method invocations
    @Test
    public void basicMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m1 = ckClass.getMethod("m1/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m1/0", qualifier + "m2/1[int]", "MethodInvocation.Simple2.x/0"),
                m1.getMethodInvocations());

        CKMethodResult m2 = ckClass.getMethod("m2/1[int]").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m3/1[java.lang.String]", "MethodInvocation.GO2.magic/0"),
                m2.getMethodInvocations());

        CKMethodResult m3 = ckClass.getMethod("m3/1[java.lang.String]").get();
        Assert.assertEquals(
                Sets.newHashSet("java.io.PrintStream.println/1[int]", qualifier + "m4/2[java.lang.String,int]"),
                m3.getMethodInvocations());

        CKMethodResult m4 = ckClass.getMethod("m4/2[java.lang.String,int]").get();
        Assert.assertEquals(
                Sets.newHashSet("MethodInvocation.Simple2.x/0", qualifier + "m2/1[int]"),
                m4.getMethodInvocations());
    }

    @Test
    public void ifBlockMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m5 = ckClass.getMethod("m5/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]"),
                m5.getMethodInvocations());
    }

    @Test
    public void returnMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m6 = ckClass.getMethod("m6/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m4/2[java.lang.String,int]"),
                m6.getMethodInvocations());
    }

    @Test
    public void staticMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m7 = ckClass.getMethod("m7/0").get();
        Assert.assertEquals(
                Sets.newHashSet("MethodInvocation.GO2.boring/0"),
                m7.getMethodInvocations());
    }

    @Test
    public void lambdaMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m8 = ckClass.getMethod("m8/0").get();
        Assert.assertEquals(
                Sets.newHashSet("java.util.Collection<java.lang.String>.stream/0",
                        "java.util.stream.Stream<java.lang.String>.map/1[java.util.function.Function<? super java.lang.String,? extends java.lang.Integer>]",
                        "MethodInvocation.Simple2.x/0",
                        "java.util.stream.Collectors.toList/0",
                        "java.util.stream.Stream<java.lang.Integer>.collect/1[java.util.stream.Collector<? super java.lang.Integer,java.lang.Object,java.util.List<java.lang.Integer>>]",
                        qualifier + "m4/2[java.lang.String,int]"),
                m8.getMethodInvocations());
    }

    @Test
    public void anonymousClassMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m9 = ckClass.getMethod("m9/0").get();
        Assert.assertEquals(
                Sets.newHashSet("MethodInvocation.Complex1.Complex2.n1/0"),
                m9.getMethodInvocations());
    }

    //Test local method invocations
    @Test
    public void basicMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m1 = ckClass.getMethod("m1/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m1/0", qualifier + "m2/1[int]"),
                m1.getMethodInvocationsLocal());

        CKMethodResult m2 = ckClass.getMethod("m2/1[int]").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m3/1[java.lang.String]"),
                m2.getMethodInvocationsLocal());

        CKMethodResult m3 = ckClass.getMethod("m3/1[java.lang.String]").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m4/2[java.lang.String,int]"),
                m3.getMethodInvocationsLocal());

        CKMethodResult m4 = ckClass.getMethod("m4/2[java.lang.String,int]").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]"),
                m4.getMethodInvocationsLocal());
    }

    @Test
    public void ifBlockMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m5 = ckClass.getMethod("m5/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]"),
                m5.getMethodInvocationsLocal());
    }

    @Test
    public void returnMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m6 = ckClass.getMethod("m6/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m4/2[java.lang.String,int]"),
                m6.getMethodInvocationsLocal());
    }

    @Test
    public void staticMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m7 = ckClass.getMethod("m7/0").get();
        Assert.assertEquals(
                Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY),
                m7.getMethodInvocationsLocal());
    }

    @Test
    public void lambdaMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m8 = ckClass.getMethod("m8/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m4/2[java.lang.String,int]"),
                m8.getMethodInvocationsLocal());
    }

    @Test
    public void anonymousClassMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m9 = ckClass.getMethod("m9/0").get();
        Assert.assertEquals(
                Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY),
                m9.getMethodInvocationsLocal());
    }

    //Test indirect local method invocations
    @Test
    public void basicMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m1 = ckClass.getMethod("m1/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m3/1[java.lang.String]", qualifier + "m4/2[java.lang.String,int]"),
                m1.getMethodInvocationsIndirectLocal());

        CKMethodResult m2 = ckClass.getMethod("m2/1[int]").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m4/2[java.lang.String,int]"),
                m2.getMethodInvocationsIndirectLocal());

        CKMethodResult m3 = ckClass.getMethod("m3/1[java.lang.String]").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]"),
                m3.getMethodInvocationsIndirectLocal());

        CKMethodResult m4 = ckClass.getMethod("m4/2[java.lang.String,int]").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m3/1[java.lang.String]"),
                m4.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void ifBlockMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m5 = ckClass.getMethod("m5/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m3/1[java.lang.String]", qualifier + "m4/2[java.lang.String,int]"),
                m5.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void returnMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m6 = ckClass.getMethod("m6/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]", qualifier + "m3/1[java.lang.String]"),
                m6.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void staticMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m7 = ckClass.getMethod("m7/0").get();
        Assert.assertEquals(
                Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY),
                m7.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void lambdaMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m8 = ckClass.getMethod("m8/0").get();
        Assert.assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]", qualifier + "m3/1[java.lang.String]"),
                m8.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void anonymousClassMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m9 = ckClass.getMethod("m9/0").get();
        Assert.assertEquals(
                Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY),
                m9.getMethodInvocationsIndirectLocal());
    }
}