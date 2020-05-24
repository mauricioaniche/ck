package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MethodsTest extends BaseTest {

    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/methods");
    }

    @Test
    public void all() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(9, a.getNumberOfMethods());
    }

    @Test
    public void allPublic() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(4, a.getNumberOfPublicMethods());
    }

    @Test
    public void allStatic() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(2, a.getNumberOfStaticMethods());
    }

    @Test
    public void allDefault() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(1, a.getNumberOfDefaultMethods());
    }

    @Test
    public void allPrivate() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(3, a.getNumberOfPrivateMethods());
    }

    @Test
    public void allProtected() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(1, a.getNumberOfProtectedMethods());
    }


    @Test
    public void allSynchronized() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(1, a.getNumberOfSynchronizedMethods());
    }

    @Test
    public void allAbstract() {
        CKClassResult a = report.get("methods.Methods2");
        Assertions.assertEquals(3, a.getNumberOfMethods());
        Assertions.assertEquals(2, a.getNumberOfAbstractMethods());
    }

    @Test
    public void constructors() {
        CKClassResult a = report.get("methods.Methods3");
        Assertions.assertEquals(3, a.getNumberOfMethods());
        Assertions.assertEquals(3, a.getMethods().size());

        CKMethodResult m1 = a.getMethods().stream().filter(x -> x.getMethodName().equals("Methods3/0")).findFirst().get();
        Assertions.assertTrue(m1.isConstructor());

        CKMethodResult m2 = a.getMethods().stream().filter(x -> x.getMethodName().equals("Methods3/1[int]")).findFirst().get();
        Assertions.assertTrue(m2.isConstructor());

        CKMethodResult m3 = a.getMethods().stream().filter(x -> x.getMethodName().equals("a/0")).findFirst().get();
        Assertions.assertFalse(m3.isConstructor());
    }

    @Test
    public void staticInitializer() {
        CKClassResult a = report.get("methods.StaticInitializer");

        Optional<CKMethodResult> init = a.getMethod("(initializer 1)");
        Assertions.assertTrue(init.isPresent());
        Assertions.assertEquals(1, init.get().getLoopQty());
    }

    // there can be multiple static initializers in a single class
    // see https://github.com/mauricioaniche/ck/issues/53
    @Test
    public void multipleStaticInitializer() {
        CKClassResult a = report.get("methods.StaticInitializer2");

        Optional<CKMethodResult> init1 = a.getMethod("(initializer 1)");
        Assertions.assertTrue(init1.isPresent());
        Assertions.assertEquals(1, init1.get().getLoopQty());

        Optional<CKMethodResult> init2 = a.getMethod("(initializer 2)");
        Assertions.assertTrue(init2.isPresent());
        Assertions.assertEquals(0, init2.get().getLoopQty());
    }

    @Test
    public void allFinal() {
        CKClassResult a = report.get("methods.Methods");
        Assertions.assertEquals(1, a.getNumberOfFinalMethods());
    }
}
