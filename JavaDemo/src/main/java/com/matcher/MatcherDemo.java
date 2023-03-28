package com.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/7/28
 *
 */
public class MatcherDemo {
    public static void main(String args[]) {
        String str = "B8:60:FE:10:21:FE";
        String pattern = "((([aA-fF0-9]{2}:){5})|(([aA-fF0-9]{2}-){5}))[aA-fF0-9]{2}";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
