package com.example.test.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


//获取日期，来得到学期，年龄
public class DateUtil {
    public static String getSemester() {
        String a = "-1";
        String b = "-2";
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        if (m <= 6) {
            return y + a;
        } else {
            return y + b;
        }
    }

    public static boolean isChoose(String a, String b) {

        Date date=new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        if (a.compareTo(dateString)<=0&&b.compareTo(dateString)>=0){
            return true;
        }else{
            return false;
        }

    }


}
