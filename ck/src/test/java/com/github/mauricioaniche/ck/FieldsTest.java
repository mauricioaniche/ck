package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FieldsTest extends BaseTest {

    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/fields");
    }

    @Test
    public void all() {
        CKClassResult a = report.get("fields.Fields");
        Assertions.assertEquals(10, a.getNumberOfFields());
    }

    @Test
    public void allPublic() {
        CKClassResult a = report.get("fields.Fields");
        Assertions.assertEquals(3, a.getNumberOfPublicFields());
    }

    @Test
    public void allStatic() {
        CKClassResult a = report.get("fields.Fields");
        Assertions.assertEquals(3, a.getNumberOfStaticFields());
    }


    @Test
    public void allPrivate() {
        CKClassResult a = report.get("fields.Fields");
        Assertions.assertEquals(4, a.getNumberOfPrivateFields());
    }


    @Test
    public void allDefault() {
        CKClassResult a = report.get("fields.Fields");
        Assertions.assertEquals(2, a.getNumberOfDefaultFields());
    }

    @Test
    public void allSynchronized() {
        CKClassResult a = report.get("fields.Fields");
        Assertions.assertEquals(1, a.getNumberOfSynchronizedFields());
    }

    @Test
    public void allProtected() {
        CKClassResult a = report.get(("fields.Fields"));
        Assertions.assertEquals(1, a.getNumberOfProtectedFields());
    }

    @Test
    public void AllFinal() {
        CKClassResult a = report.get(("fields.Fields"));
        Assertions.assertEquals(1, a.getNumberOfFinalFields());

    }
}
