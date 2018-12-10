package com.kt.gigaiot_sdk;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.Device;
import com.kt.gigaiot_sdk.data.PushApiResponse;
import com.kt.gigaiot_sdk.data.PushTypePair;
import com.kt.gigaiot_sdk.error.AccesTokenNullException;
import com.kt.gigaiot_sdk.error.ReqParamException;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.gigaiot_sdk.network.HttpTransport;
import com.kt.gigaiot_sdk.network.JsonHandler;
import com.kt.gigaiot_sdk.network.data.SvrResponse;
import com.kt.gigaiot_sdk.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 6. 18..
 */
public class PushApi {

    private final String TAG = PushApi.class.getSimpleName();

    public static final String PUSH_TYPE_GCM        = "01";

    public static final String PUSH_MSG_TYPE_COLLECT    = "01";
    public static final String PUSH_MSG_TYPE_COMPLEX    = "02";
    public static final String PUSH_MSG_TYPE_OUTBREAK   = "03";
    public static final String PUSH_MSG_TYPE_ECSND      = "04";

    private String mAccessToken;

    public PushApi(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public PushApiResponse gcmSessionRegistration(String mbrSeq, String applId,
                                                  String applUuidVal, ArrayList<PushTypePair> pushTypePairs){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(mbrSeq, applId, applUuidVal) || pushTypePairs == null || pushTypePairs.size() == 0){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = ApiConstants.OPEN_API_POST_PUSH_SESSION_REG;

        String requestJson = null;

        try {

            JSONObject requestObj = new JSONObject();
            requestObj.put("pushTypeCd", PUSH_TYPE_GCM);
            requestObj.put("mbrSeq", mbrSeq);
            requestObj.put("cretrId", mbrSeq);
            requestObj.put("amdrId", mbrSeq);
            requestObj.put("applId", applId);
            requestObj.put("applUuidVal", applUuidVal);
            requestObj.put("cretrId", mbrSeq);
            requestObj.put("amdrId", mbrSeq);

            JSONArray array = new JSONArray();

            for(PushTypePair pair : pushTypePairs) {

                JSONObject subscriptionObj = new JSONObject();
                subscriptionObj.put("svcTgtSeq", pair.getSvcTgtSeq());
                subscriptionObj.put("msgTypeCd", pair.getMsgTypeCd());

                array.put(subscriptionObj);
            }

            requestObj.put("subscriptions", array);

            requestJson = requestObj.toString();

            android.util.Log.d(TAG, "gcmSessionRegistration requestJson = " + requestJson);

        }catch (Exception e){
            e.printStackTrace();
        }

        String strResult = new HttpTransport().postJsonToURL(mAccessToken, url, requestJson);
        SvrResponse<Device> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Device>>() {
        }.getType());

        ArrayList<Device> arrayResult = new ArrayList<>();
        arrayResult.add(result.getData());

        return null;
    }

    public PushApiResponse gcmSessionDelete(String applUuidVal){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(applUuidVal)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_POST_PUSH_SESSION_DEL, applUuidVal);

        String strResult = new HttpTransport().deleteFromURL(mAccessToken, url);
        SvrResponse<Device> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Device>>() {
        }.getType());

        ArrayList<Device> arrayResult = new ArrayList<>();
        arrayResult.add(result.getData());

        return null;
    }

    private void checkAccessToken(){
        if(mAccessToken == null || mAccessToken.equals("")){
            throw new AccesTokenNullException(AccesTokenNullException.DEFAULT_MSG);
        }
    }

}
