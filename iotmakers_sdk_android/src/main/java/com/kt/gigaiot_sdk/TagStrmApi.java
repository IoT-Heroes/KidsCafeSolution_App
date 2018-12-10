package com.kt.gigaiot_sdk;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.Data;
import com.kt.gigaiot_sdk.data.Log;
import com.kt.gigaiot_sdk.data.TagStrm;
import com.kt.gigaiot_sdk.data.TagStrmApiResponse;
import com.kt.gigaiot_sdk.error.AccesTokenNullException;
import com.kt.gigaiot_sdk.error.ReqParamException;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.gigaiot_sdk.network.HttpTransport;
import com.kt.gigaiot_sdk.network.JsonHandler;
import com.kt.gigaiot_sdk.network.data.SvrResponse;
import com.kt.gigaiot_sdk.util.Utils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 3. 24..
 */
public class TagStrmApi {

    private final String TAG = TagStrmApi.class.getSimpleName();

    public static final String TAGSTRM_DATA = "0000010";
    public static final String TAGSTRM_CTRL = "0000020";

    private String mAccessToken = null;

    public TagStrmApi(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public TagStrmApiResponse getTagStrmList(String spotDevId){

        //error handling - user access token missing
        checkAccessToken();

        if(!Utils.isValidParams(spotDevId)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        //String url = Utils.makeURL(ApiConstants.OPEN_API_GET_TAGSTREAM_LIST2, svcTgtSeq, spotDevSeq, "1", "10");
        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_TAGSTREAM_LIST, spotDevId, "1", "10");
        android.util.Log.i(TAG,url);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<TagStrm>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<TagStrm>>>() {
        }.getType());

        if(result.getData() != null) {
            return new TagStrmApiResponse(result.getResponseCode(), result.getMessage(), result.getData().getRows(), null);
        }else{
            return new TagStrmApiResponse(result.getResponseCode(), result.getMessage(), null, null);
        }


    }

    public TagStrmApiResponse getTagStrmLog(String spotDevId, String wantPeriod, String Count){

        //error handling - user access token missing
        checkAccessToken();

        if(!Utils.isValidParams(spotDevId, wantPeriod, Count)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_TAGSTREAM_LOG, spotDevId, wantPeriod, Count);
        android.util.Log.i(TAG,url);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<ArrayList<Log>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<ArrayList<Log>>>() {
        }.getType());

        return new TagStrmApiResponse(result.getResponseCode(), result.getMessage(), null, result.getData());
    }

    public TagStrmApiResponse getTagStrmLog(String svcTgtSeq, String spotDevSeq){

        //error handling - user access token missing
        checkAccessToken();

        if(!Utils.isValidParams(spotDevSeq, svcTgtSeq)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_TAGSTREAM_LOG_LAST, svcTgtSeq, spotDevSeq);
        android.util.Log.i(TAG,url);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<ArrayList<Log>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<ArrayList<Log>>>() {
        }.getType());

        return new TagStrmApiResponse(result.getResponseCode(), result.getMessage(), null, result.getData());
    }


    public TagStrmApiResponse sendCtrlMsg(String svcTgtSeq, String spotDevSeq, String spotDevId,
                                          String gwCnctId, String tagStrmId, String tagStrmValTypeCd,
                                          String mbrId, String ctrlMsg){

        //error handling - user access token missing
        checkAccessToken();

        if(!Utils.isValidParams(svcTgtSeq, spotDevSeq, spotDevId, gwCnctId, tagStrmId, tagStrmValTypeCd, mbrId, ctrlMsg)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_POST_TAGSTREAM_CTRL, svcTgtSeq, spotDevSeq);
        android.util.Log.i(TAG,url);
        String requestJson = null;

        try {

            JSONObject requestObj = new JSONObject();
            requestObj.put("mbrId", mbrId);
            requestObj.put("spotDevId", spotDevId);
            requestObj.put("tagStrmValTypeCd", tagStrmValTypeCd);
            requestObj.put("gwCnctId", gwCnctId);
            requestObj.put("tagStrmId", tagStrmId);
            requestObj.put("ctrlMsg", ctrlMsg);

            requestJson = requestObj.toString();

            android.util.Log.d(TAG, "sendCtrlMsg requestJson = " + requestJson);

        }catch (Exception e){

        }

        String strResult = new HttpTransport().postJsonToURL(mAccessToken, url, requestJson);
        SvrResponse<Void> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Void>>() {
        }.getType());

        return new TagStrmApiResponse(result.getResponseCode(), result.getMessage(), null, null);

    }

    private void checkAccessToken(){
        if(mAccessToken == null || mAccessToken.equals("")){
            throw new AccesTokenNullException(AccesTokenNullException.DEFAULT_MSG);
        }
    }


}
