package com.example.dxkj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sh on 2015/9/16.
 */
public class TimeUtils {

    public static String getStrTime(String time){
        Long timeLong = Long.parseLong(time);
        Date date = new Date(timeLong);
        SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return sf.format(date);

    }
}
