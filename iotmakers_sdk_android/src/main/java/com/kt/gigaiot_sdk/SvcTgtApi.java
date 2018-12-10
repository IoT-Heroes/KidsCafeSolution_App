package com.kt.gigaiot_sdk;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.Data;
import com.kt.gigaiot_sdk.data.SvcTgt;
import com.kt.gigaiot_sdk.data.SvcTgtApiResponse;
import com.kt.gigaiot_sdk.error.AccesTokenNullException;
import com.kt.gigaiot_sdk.error.ReqParamException;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.gigaiot_sdk.network.HttpTransport;
import com.kt.gigaiot_sdk.network.JsonHandler;
import com.kt.gigaiot_sdk.network.data.SvrResponse;
import com.kt.gigaiot_sdk.util.Utils;

/**
 * Created by ceoko on 15. 4. 9..
 */
public class SvcTgtApi {

    private final String TAG = SvcTgtApi.class.getSimpleName();

    private String mAccessToken = "test_token";


    public SvcTgtApi(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public SvcTgtApiResponse getSvcTgtSeqList(String mbrId){

        //error handling - user access token missing
        if(TextUtils.isEmpty(mAccessToken)){
            throw new AccesTokenNullException(AccesTokenNullException.DEFAULT_MSG);
        }

        //error handling - parameter missing
        if(!Utils.isValidParams(mbrId)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        //String url = Utils.makeURL(ApiConstants.API_GET_SVCTGT_SEQ_LIST, mbrId);
        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_SVCTGT_SEQ_LIST, mbrId, "1", "10");
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        Log.i(TAG, "getSvcTgtSeqList strResult = " + strResult);
        SvrResponse<Data<SvcTgt>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<SvcTgt>>>() {}.getType());
        if (result.getResponseCode().equals("NG") && result.getMessage().equals("Unauthorized")) {
//            throw new UnauthorizedException(result.getMessage());
            return new SvcTgtApiResponse(result.getResponseCode(), result.getMessage(), null);
        }

        return new SvcTgtApiResponse(result.getResponseCode(), result.getMessage(), result.getData().getRows());
    }

}
