package com.example.test.util;

import java.util.Calendar;
import java.util.Random;

public class IdUtil {
    public static String generateId(){
        Calendar cal = Calendar.getInstance();
        String result = "";
        int y = cal.get(Calendar.YEAR);
        Random random = new Random();
        result+=y;
        for(int i=0;i<8;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }
}
