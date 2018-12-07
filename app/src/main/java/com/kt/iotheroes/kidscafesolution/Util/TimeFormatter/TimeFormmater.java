package com.kt.iotheroes.kidscafesolution.Util.TimeFormatter;

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
}

