package com.github.mauricioaniche.ck;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LooseClassCohesionTest extends BaseTest {

    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/ClassCohesion");
    }

    @Test
    public void highCohesion() {
        CKClassResult ckClass = report.get("ClassCohesion.HighCohesion");
        Assert.assertEquals(1f, ckClass.getLooseClassCohesion(),0.0000001);
    }

    @Test
    public void noCohesion() {
        CKClassResult ckClass = report.get("ClassCohesion.NoCohesion");
        Assert.assertEquals(0f, ckClass.getLooseClassCohesion(),0.0000001);
    }

    @Test
    public void mediumCohesion1() {
        CKClassResult ckClass = report.get("ClassCohesion.MediumCohesion");
        Assert.assertEquals(2f / 6f, ckClass.getLooseClassCohesion(),0.0000001);
    }

    @Test
    public void mediumCohesion2() {
        CKClassResult ckClass = report.get("ClassCohesion.Simple1");
        Assert.assertEquals(2f / 10f, ckClass.getLooseClassCohesion(),0.0000001);
    }

    @Test
    public void mediumCohesion3() {
        CKClassResult ckClass = report.get("ClassCohesion.Simple2");
        Assert.assertEquals(6f / 10f, ckClass.getLooseClassCohesion(),0.0000001);
    }

    @Test
    public void hugeClassCohesion(){
        Map<String, CKClassResult> reportHuge = run(fixturesDir() + "/real-world-huge-class");
        CKClassResult ckClass = reportHuge.get("com.satoshilabs.trezor.lib.protobuf.TrezorMessage");
        Assert.assertTrue(ckClass.getLooseClassCohesion() < 0.000001f);
    }

    @Test
    public void noVisibleMethods(){
        CKClassResult ckClass = report.get("ClassCohesion.NoVisibleMethods");
        Assert.assertEquals(-1, ckClass.getLooseClassCohesion(),0.0000001);
    }
}