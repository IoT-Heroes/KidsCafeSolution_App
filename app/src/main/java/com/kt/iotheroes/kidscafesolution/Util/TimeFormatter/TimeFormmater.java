package com.kt.iotheroes.kidscafesolution.Util.TimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
}

