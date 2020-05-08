package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class LocalFieldsTest extends BaseTest {
    private static Map<String, CKClassResult> report;

    @BeforeAll
    public static void setUp() {
        report = run(fixturesDir() + "/fieldusage");
    }

    @Test
    public void localFieldsTest() {
        CKClassResult a = report.get("fieldusage.FieldUsage");
        Assertions.assertEquals( Sets.newHashSet("a", "b", "c", "d", "xx"), a.getFieldNames());
    }
}