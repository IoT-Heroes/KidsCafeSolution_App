package com.kt.gigaiot_sdk.util;

import android.util.Base64;

import com.kt.gigaiot_sdk.network.ApiConstants;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by ceoko on 15. 3. 30..
 */
public class Utils {

    /**
     * Throwable 데이터를 String으로 변환.
     * @param ex
     * @return
     */
    public static String getStackTrace(Throwable ex){

        final Writer result = new StringWriter();

        final PrintWriter printWriter = new PrintWriter(result);

        Throwable cause = ex;

        while(cause != null){

            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        final String stackTraceAsString = result.toString();

        printWriter.close();

        return stackTraceAsString;
    }

    public static String makeURL(String url, Object... args){

        return String.format(url, args);
    }

    public static boolean isValidParams(String... params){

        for(String param : params){

            if(param == null || param.equals("")){
                return false;
            }
        }

        return true;

    }

    public static boolean isValidParams(Object... params){

        for(Object param : params){

            if(param == null){
                return false;
            }
        }

        return true;

    }

    public static String getBase64encode(String content){
        return Base64.encodeToString(content.getBytes(), 0);
    }

}
