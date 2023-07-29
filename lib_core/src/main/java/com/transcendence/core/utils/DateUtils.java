package com.transcendence.core.utils;

/**
 * @author joephone
 * @date 2022/12/11
 * @desc
 */
public class DateUtils {

    /**
     *  计算天数
     * @param time 两时间之差
     * @return
     */
    public static long day(long time){
        return time/1000 / (60 * 60 * 24);
    }

    public static long second(long time){
        return time/1000;
    }

}
