package com.halyn.util;

public class TextUtil {
    public static String removeSpace(String text) {
        String pattern = "\\s+";
        String result = text.replaceAll(pattern, "");

        return result;
    }

    public static String removeLine(String text) {
        String pattern = "\\n|\\r";
        String result = text.replaceAll(pattern, "");
        return result;
    }

    public static String removeSpaceAndLine(String text) {
        String pattern = "\\n|\\r|\\s+";
        String result = text.replaceAll(pattern, "");
        return result;
    }


}