package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalFieldsTest extends BaseTest {
    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/fieldusage");
    }

    @Test
    public void localFieldsTest() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        Assertions.assertEquals( Sets.newHashSet("a", "b", "c", "d", "xx"), a.getFieldNames());
    }
}