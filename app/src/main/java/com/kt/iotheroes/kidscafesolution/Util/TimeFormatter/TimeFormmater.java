package com.kt.iotheroes.kidscafesolution.Util.TimeFormatter;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by kksd0900 on 16. 10. 16..
 */
public class TimeFormmater {
    public static String getCurrentTime_UTC() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date());
    }

    public static Date getDateFromString(String strDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTime(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static long getTodayStartTimestamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strCurrentTime = df.format(currentTime);

        Log.i("time_test", "getTodayStartTimestamp strCurrentTime = " + strCurrentTime);

        Date todayStartTime = null;

        try {
            todayStartTime = df.parse(strCurrentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(todayStartTime != null)
            return todayStartTime.getTime();
        else
            return -1;

    }

    public static String timestampToFormattedStr(long timemills){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        return df.format(new Date(timemills));
    }
}

