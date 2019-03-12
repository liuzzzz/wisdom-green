package com.secrething.core;

/**
 * Created by xiaoq on 2018-11-27 12:00.
 */
public final class StringUtils {
    private StringUtils(){
        throw new UnsupportedOperationException();
    }

    public static boolean isBlank(CharSequence cs){
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
