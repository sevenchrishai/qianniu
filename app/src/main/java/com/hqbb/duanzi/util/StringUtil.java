package com.hqbb.duanzi.util;

import android.text.TextUtils;

/**
 * Created by ylh on 2018/1/18 0018.
 */

public class StringUtil {

    public static String dataFormat(String date) {
        if (TextUtils.isEmpty(date))
            return "";
        String s[] = date.split("T");
        if (s.length >= 2) {
            String s1[] = s[1].split("\\.");
            if (s1.length >= 2) {
                return s[0] + " " + s1[0];
            } else {
                return s[0] + " " + s[1];
            }
        } else
            return s[0];
    }
}
