package com.github.mauricioaniche.ck;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

public class MethodsTest extends BaseTest {

    private static Map<String, CKClassResult> report;

    @BeforeClass
    public static void setUp() {
        report = run(fixturesDir() + "/methods");
    }

    @Test
    public void all() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(9, a.getNumberOfMethods());
    }

    @Test
    public void allPublic() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(4, a.getNumberOfPublicMethods());
    }

    @Test
    public void allStatic() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(2, a.getNumberOfStaticMethods());
    }

    @Test
    public void allDefault() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(1, a.getNumberOfDefaultMethods());
    }

    @Test
    public void allPrivate() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(3, a.getNumberOfPrivateMethods());
    }

    @Test
    public void allProtected() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(1, a.getNumberOfProtectedMethods());
    }


    @Test
    public void allSynchronized() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(1, a.getNumberOfSynchronizedMethods());
    }

    @Test
    public void allAbstract() {
        CKClassResult a = report.get("methods.Methods2");
        Assert.assertEquals(3, a.getNumberOfMethods());
        Assert.assertEquals(2, a.getNumberOfAbstractMethods());
    }

    @Test
    public void constructors() {
        CKClassResult a = report.get("methods.Methods3");
        Assert.assertEquals(3, a.getNumberOfMethods());
        Assert.assertEquals(3, a.getMethods().size());

        CKMethodResult m1 = a.getMethods().stream().filter(x -> x.getMethodName().equals("Methods3/0")).findFirst().get();
        Assert.assertTrue(m1.isConstructor());

        CKMethodResult m2 = a.getMethods().stream().filter(x -> x.getMethodName().equals("Methods3/1[int]")).findFirst().get();
        Assert.assertTrue(m2.isConstructor());

        CKMethodResult m3 = a.getMethods().stream().filter(x -> x.getMethodName().equals("a/0")).findFirst().get();
        Assert.assertFalse(m3.isConstructor());
    }

    @Test
    public void staticInitializer() {
        CKClassResult a = report.get("methods.StaticInitializer");

        Optional<CKMethodResult> init = a.getMethod("(initializer)");
        Assert.assertTrue(init.isPresent());
        Assert.assertEquals(1, init.get().getLoopQty());
    }

    @Test
    public void allFinal() {
        CKClassResult a = report.get("methods.Methods");
        Assert.assertEquals(1, a.getNumberOfFinalMethods());
    }
}
