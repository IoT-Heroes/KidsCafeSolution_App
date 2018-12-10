package com.kt.gigaiot_sdk;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.BindType;
import com.kt.gigaiot_sdk.data.Data;
import com.kt.gigaiot_sdk.data.Device;
import com.kt.gigaiot_sdk.data.DeviceApiResponse;
import com.kt.gigaiot_sdk.data.Protocol;
import com.kt.gigaiot_sdk.data.ProtocolApiResponse;
import com.kt.gigaiot_sdk.data.RootGw;
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
public class ProtocolApi {

    private final String TAG = ProtocolApi.class.getSimpleName();

    private String mAccessToken;

    public ProtocolApi(String accessToken) {
        this.mAccessToken = accessToken;
    }

    /**
     * 프로토콜 리스트 가져오기
     */
    public ProtocolApiResponse getProtocolList(){

        //error handling - user access token missing
        checkAccessToken();

        String url = ApiConstants.OPEN_API_GET_PROTOCOLS;
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<Protocol>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<Protocol>>>(){}.getType());

        return new ProtocolApiResponse(result.getResponseCode(), result.getMessage(), result.getData().getRows());

    }


    /**
     * BindType 리스트 가져오기
     */
    public ProtocolApiResponse getBindTypeList(String protId){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(protId)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_BINDTYPES, protId);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<BindType>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<BindType>>>(){}.getType());

        return new ProtocolApiResponse(result.getResponseCode(), result.getMessage(), null, result.getData().getRows());

    }

    /**
     * 상위 게이트웨이 연결 아이디 가져오기
     */
    public ProtocolApiResponse getRootGwCncId(String svcTgtSeq, String protId, String bindTypeCd){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(svcTgtSeq, protId, bindTypeCd)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_ROOTGWCNCID, svcTgtSeq, protId, bindTypeCd);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<RootGw>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<RootGw>>>(){}.getType());

        return new ProtocolApiResponse(result.getResponseCode(), result.getMessage(), null, null,result.getData().getRows());

    }

    private void checkAccessToken(){
        if(mAccessToken == null || mAccessToken.equals("")){
            throw new AccesTokenNullException(AccesTokenNullException.DEFAULT_MSG);
        }
    }


}
