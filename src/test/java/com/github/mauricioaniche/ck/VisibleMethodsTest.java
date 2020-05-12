package com.github.mauricioaniche.ck;

import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class VisibleMethodsTest extends BaseTest{
    private static Map<String, CKClassResult> report;

    @BeforeAll
    public static void setUp() {
        report = run(fixturesDir() + "/VisibleMethods");
    }

    @Test
    public void countVisible() {
        CKClassResult a = report.get("VisibleMethods.VisibleMethods");
        Assert.assertEquals(6, a.getVisibleMethods().size());
    }

    @Test
    public void countNonVisible() {
        CKClassResult a = report.get("VisibleMethods.VisibleMethods");
        Assert.assertEquals(4, a.getNumberOfMethods() - a.getNumberOfVisibleMethods());
    }

    @Test
    public void visibleMethods() {
        CKClassResult a = report.get("VisibleMethods.VisibleMethods");
        Set<String> visibleMethodsFound = a.getVisibleMethods().stream().map(method -> method.getMethodName()).collect(Collectors.toSet());
        Assert.assertEquals(Sets.newHashSet("m3/0", "m4/0", "m6/0", "m7/0", "m10/0", "m8/0"), visibleMethodsFound);
    }
}
