package com.example.test.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
判断是否电话号码是否格式正确
 */
public class InfoCorr {
    public static boolean isMobileNum(String telNum){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(telNum);
        return m.matches();
    }

}
