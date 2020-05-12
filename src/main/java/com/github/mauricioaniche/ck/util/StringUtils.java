package com.github.mauricioaniche.ck.util;

public class StringUtils {

    // https://stackoverflow.com/questions/2850203/count-the-number-of-lines-in-a-java-string
    public static int countLines(String str) {
        if(str == null || str.isEmpty())
        {
            return 0;
        }
        int lines = 1;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        return lines;
    }
}