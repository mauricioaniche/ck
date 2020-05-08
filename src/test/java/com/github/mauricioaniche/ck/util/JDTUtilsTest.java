package com.github.mauricioaniche.ck.util;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static com.github.mauricioaniche.ck.util.JDTUtils.getVariableName;

public class JDTUtilsTest {
    @Test
    public void extractVariableName(){
        String name1 = getVariableName(Arrays.asList("a=10;"));
        Assert.assertEquals("a", name1);

        String name2 = getVariableName(Arrays.asList("b;"));
        Assert.assertEquals("b", name2);

        String name3 = getVariableName(Arrays.asList("c=b+10;"));
        Assert.assertEquals("c", name3);

        String name4 = getVariableName(Arrays.asList("c10=15;"));
        Assert.assertEquals("c10", name4);

        String name5 = getVariableName(Arrays.asList("date = new Date();"));
        Assert.assertEquals("date", name5);

        String name6 = getVariableName(Arrays.asList("类型这个的时间 = require('@/config').value;"));
        Assert.assertEquals("类型这个的时间", name6);

        String name7 = getVariableName(Arrays.asList(" a    = 10;"));
        Assert.assertEquals("a", name7);

        String name8 = getVariableName(Arrays.asList("" +
                "a    = 10;"));
        Assert.assertEquals("a", name8);

        String name9 = getVariableName(Arrays.asList("a" +
                "= 10;"));
        Assert.assertEquals("a", name9);

        String name10 = getVariableName(Arrays.asList("xx"));
        Assert.assertEquals("xx", name10);
    }
}