package com.kt.gigaiot_sdk;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.Data;
import com.kt.gigaiot_sdk.data.Event;
import com.kt.gigaiot_sdk.data.EventApiResponse;
import com.kt.gigaiot_sdk.data.EventLog;
import com.kt.gigaiot_sdk.error.AccesTokenNullException;
import com.kt.gigaiot_sdk.error.ReqParamException;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.gigaiot_sdk.network.HttpTransport;
import com.kt.gigaiot_sdk.network.JsonHandler;
import com.kt.gigaiot_sdk.network.data.SvrResponse;
import com.kt.gigaiot_sdk.util.Utils;

/**
 * Created by ceoko on 15. 3. 23..
 */
public class EventApi {

    private final String TAG = EventApi.class.getSimpleName();

    private String mAccessToken;

    public EventApi(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public EventApiResponse getEventList(String mbrId, String svcTgt){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(mbrId, svcTgt)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        //String url = Utils.makeURL(ApiConstants.API_GET_DEVICE_LIST, svcTgtSeq);
        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_EVENT_LIST, svcTgt, mbrId);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<Event>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<Event>>>(){}.getType());

        if(result.getResponseCode() != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {

            return new EventApiResponse(result.getResponseCode(), result.getMessage(), result.getData().getRows());

        }else{
            return new EventApiResponse(result.getResponseCode(), result.getMessage(), null);
        }
    }

    public EventApiResponse getEventLogList(String spotDevSeq, String svcTgtSeq, String eventId, long startTime){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(spotDevSeq, svcTgtSeq, eventId, startTime)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_EVENT_LOG_LIST, spotDevSeq, svcTgtSeq, eventId, startTime);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<EventLog>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<EventLog>>>(){}.getType());

        if(result.getResponseCode() != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {

            return new EventApiResponse(result.getResponseCode(), result.getMessage(), null, result.getData().getRows());
        }else{
            return new EventApiResponse(result.getResponseCode(), result.getMessage(), null);
        }

    }

    public EventApiResponse getEventLogList(String spotDevSeq, String svcTgtSeq, String eventId, long startTime, long endTime){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(spotDevSeq, svcTgtSeq, eventId, startTime, endTime)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_EVENT_LOG_LIST_TO_START, spotDevSeq, svcTgtSeq, eventId, startTime, endTime);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<EventLog>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<EventLog>>>(){}.getType());

        if(result.getResponseCode() != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {

            return new EventApiResponse(result.getResponseCode(), result.getMessage(), null, result.getData().getRows());
        }else{
            return new EventApiResponse(result.getResponseCode(), result.getMessage(), null);
        }

    }

    private void checkAccessToken(){
        if(mAccessToken == null || mAccessToken.equals("")){
            throw new AccesTokenNullException(AccesTokenNullException.DEFAULT_MSG);
        }
    }


}
