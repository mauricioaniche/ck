package com.github.mauricioaniche.ck.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //Use the given regex pattern to extract a single substring from the given String
    //Returns the empty string in case of no matching
    public static String substringRegex(String string, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if(matcher.find())
            return matcher.group();
        return "";
    }
}