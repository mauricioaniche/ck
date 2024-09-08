package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalFieldsAccessedTest extends BaseTest {
    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/fieldusage");
    }

    //test local field accesses
    @Test
    public void localFieldsAccessedBasic() {
        CKClassResult a = report.get("fieldusage.FieldUsage");

        //Test basic fields access
        CKMethodResult m1 = a.getMethod("m1/0").get();
        Assertions.assertEquals( Sets.newHashSet("a", "b"), m1.getFieldsAccessed());

        //Test field name overloading
        CKMethodResult m2 = a.getMethod("m2/0").get();
        Assertions.assertEquals(Sets.newHashSet("a"), m2.getFieldsAccessed());

        //Test field reference with this
        CKMethodResult m3 = a.getMethod("m3/0").get();
        Assertions.assertEquals(Sets.newHashSet("a"), m3.getFieldsAccessed());
    }

    @Test
    public void localFieldsThatAreDeclaredAfter() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        CKMethodResult m4 = a.getMethod("m4/0").get();
        Assertions.assertEquals(Sets.newHashSet("xx"), m4.getFieldsAccessed());
    }

    @Test
    public void localLambdaFunction(){
        CKClassResult a = report.get("fieldusage.FieldUsage");
        CKMethodResult m5 = a.getMethod("m5/0").get();
        Assertions.assertEquals(Sets.newHashSet("b"), m5.getFieldsAccessed());
    }

    @Test
    public void localIfBlock() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        CKMethodResult m6 = a.getMethod("m6/0").get();
        Assertions.assertEquals(Sets.newHashSet("a", "b", "c"), m6.getFieldsAccessed());
    }

    @Test
    public void localReturnStatement() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        CKMethodResult m7 = a.getMethod("m7/0").get();
        Assertions.assertEquals(Sets.newHashSet("a", "b"), m7.getFieldsAccessed());
    }

    @Test
    public void localNonLocalAccess() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        CKMethodResult m8 = a.getMethod("m8/0").get();
        Assertions.assertEquals(Sets.newHashSet("b"), m8.getFieldsAccessed());
    }

    @Test
    public void nonLocalQualifiedAccess() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        CKMethodResult m9 = a.getMethod("m9/0").get();
        Assertions.assertEquals(Sets.newHashSet(), m9.getFieldsAccessed());
    }

    @Test
    public void localThisQualifiedAccess() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        CKMethodResult m10 = a.getMethod("m10/0").get();
        Assertions.assertEquals(Sets.newHashSet("a"), m10.getFieldsAccessed());
    }
}