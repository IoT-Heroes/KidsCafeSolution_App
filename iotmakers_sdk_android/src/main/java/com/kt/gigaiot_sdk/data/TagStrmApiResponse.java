package com.kt.gigaiot_sdk.data;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 4. 10..
 */
public class TagStrmApiResponse extends Response{

    private ArrayList<TagStrm> tagStrms;
    private ArrayList<Log> logs;

    public TagStrmApiResponse(String responseCode, String message, ArrayList<TagStrm> tagStrms, ArrayList<Log> logs) {
        super(responseCode, message);
        this.tagStrms = tagStrms;
        this.logs = logs;
    }

    public ArrayList<TagStrm> getTagStrms() {
        return tagStrms;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }
}
