package com.kt.gigaiot_sdk;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.Data;
import com.kt.gigaiot_sdk.data.Device;
import com.kt.gigaiot_sdk.data.DeviceApiResponse;
import com.kt.gigaiot_sdk.data.Member;
import com.kt.gigaiot_sdk.data.MemberApiResponse;
import com.kt.gigaiot_sdk.error.AccesTokenNullException;
import com.kt.gigaiot_sdk.error.ReqParamException;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.gigaiot_sdk.network.HttpTransport;
import com.kt.gigaiot_sdk.network.JsonHandler;
import com.kt.gigaiot_sdk.network.data.SvrResponse;
import com.kt.gigaiot_sdk.util.Utils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ceoko on 15. 3. 23..
 */
public class MemberApi {

    private final String TAG = MemberApi.class.getSimpleName();

    private String mAccessToken;

    public MemberApi(String accessToken) {
        this.mAccessToken = accessToken;
    }

    /**
     * Member Info 가져오기
     */
    public MemberApiResponse getMemberInfo(String mbrSeq){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(mbrSeq)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_MEMBER_INFO, mbrSeq);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<Member>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<Member>>>(){}.getType());

        if (result.getResponseCode().equals(ApiConstants.CODE_OK)) {

            return new MemberApiResponse(result.getResponseCode(), result.getMessage(), result.getData().getRows().get(0));

        }else{

            return new MemberApiResponse(result.getResponseCode(), result.getMessage(), null);
        }


    }

    private void checkAccessToken(){
        if(mAccessToken == null || mAccessToken.equals("")){
            throw new AccesTokenNullException(AccesTokenNullException.DEFAULT_MSG);
        }
    }


}
