package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VariableNameExtractionTest extends BaseTest {

    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/VariableDeclaration");
    }

    @Test
    public void extractVariableNames(){
        CKClassResult a = report.get("VariableDeclaration.VariableDeclaration");
        assertEquals(Sets.newHashSet("a", "b", "c", "d", "e", "f", "g", "xx"), a.getFieldNames());
    }
}