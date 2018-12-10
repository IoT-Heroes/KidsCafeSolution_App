package com.kt.gigaiot_sdk;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.AccessToken;
import com.kt.gigaiot_sdk.data.GiGaIotOAuthResponse;
import com.kt.gigaiot_sdk.error.ReqParamException;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.gigaiot_sdk.network.HttpTransport;
import com.kt.gigaiot_sdk.network.JsonHandler;
import com.kt.gigaiot_sdk.util.Utils;

/**
 * Created by ceoko on 15. 6. 12..
 */
public class GigaIotOAuth {

    private final String TAG = GigaIotOAuth.class.getSimpleName();

    private String mClientId;
    private String mClientSecret;

    public GigaIotOAuth(String clientId, String clientSecret) {
        this.mClientId = clientId;
        this.mClientSecret = clientSecret;

    }

    public GiGaIotOAuthResponse loginWithPassword(String id, String password){

        //error handling - parameter missing
        if(!Utils.isValidParams(id, password)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String strResult = new HttpTransport().getAccessToken(ApiConstants.OPEN_API_POST_OAUTH_TOKEN, mClientId, mClientSecret, id, password);

        AccessToken result = JsonHandler.fromJson(strResult, new TypeToken<AccessToken>() {}.getType());

        if(result != null){

            if(TextUtils.isEmpty(result.getError())){       //토큰 정상 발급
                return new GiGaIotOAuthResponse(ApiConstants.CODE_OK, "", result.getAccess_token(), result.getMbr_seq());

            }else{                                          //토큰 발급 오류
                return new GiGaIotOAuthResponse(ApiConstants.CODE_NG, result.getError_description(), null, null);
            }
        }else {

            return new GiGaIotOAuthResponse(ApiConstants.CODE_NG, null, null, null);
        }

    }


    public GiGaIotOAuthResponse login() {

        String strResult = new HttpTransport().getAccessToken(ApiConstants.OPEN_API_POST_OAUTH_TOKEN, mClientId, mClientSecret, null, null);
        AccessToken result = JsonHandler.fromJson(strResult, new TypeToken<AccessToken>() {
        }.getType());

        if (result != null) {

            if (TextUtils.isEmpty(result.getError())) {       //토큰 정상 발급
                return new GiGaIotOAuthResponse(ApiConstants.CODE_OK, "", result.getAccess_token(), result.getMbr_seq());

            } else {                                          //토큰 발급 오류
                return new GiGaIotOAuthResponse(ApiConstants.CODE_NG, result.getError_description(), null, null);
            }
        } else {

            return new GiGaIotOAuthResponse(ApiConstants.CODE_NG, null, null, null);
        }
    }


}
