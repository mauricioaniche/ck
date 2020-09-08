package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VisibleMethodsTest extends BaseTest{

    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/VisibleMethods");
    }

    @Test
    public void countVisible() {
        CKClassResult a = report.get("VisibleMethods.VisibleMethods");
        assertEquals(8, a.getVisibleMethods().size());
    }

    @Test
    public void countNonVisible() {
        CKClassResult a = report.get("VisibleMethods.VisibleMethods");
        assertEquals(4, a.getNumberOfMethods() - a.getNumberOfVisibleMethods());
    }

    @Test
    public void visibleMethods() {
        CKClassResult a = report.get("VisibleMethods.VisibleMethods");
        Set<String> visibleMethodsFound = a.getVisibleMethods().stream().map(method -> method.getMethodName()).collect(Collectors.toSet());
        assertEquals(Sets.newHashSet("m3/0", "m4/0", "m6/0", "m7/0", "m8/0", "m10/0", "m11/0", "m12/0"), visibleMethodsFound);
    }
}