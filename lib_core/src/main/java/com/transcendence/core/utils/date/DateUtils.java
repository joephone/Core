package com.transcendence.core.utils.date;

import java.text.SimpleDateFormat;

/**
 * @author joephone
 * @date 2023/5/10
 * @desc
 */
public class DateUtils {

    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat();


    public static void DateFormat1(){
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
