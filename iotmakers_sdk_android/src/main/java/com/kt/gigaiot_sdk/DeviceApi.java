package com.kt.gigaiot_sdk;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.kt.gigaiot_sdk.data.Data;
import com.kt.gigaiot_sdk.data.Device;
import com.kt.gigaiot_sdk.data.DeviceApiResponse;
import com.kt.gigaiot_sdk.error.AccesTokenNullException;
import com.kt.gigaiot_sdk.error.ReqParamException;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.gigaiot_sdk.network.HttpTransport;
import com.kt.gigaiot_sdk.network.JsonHandler;
import com.kt.gigaiot_sdk.network.data.SvrResponse;
import com.kt.gigaiot_sdk.util.Utils;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ceoko on 15. 3. 23..
 */
public class DeviceApi {

    private final String TAG = DeviceApi.class.getSimpleName();

    private String mAccessToken;

    public DeviceApi(String accessToken) {
        this.mAccessToken = accessToken;
    }


    /**
     * Device 리스트 가져오기
     * @param
     */
    public DeviceApiResponse getDeviceList(int pageNum, int pageCon){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(pageNum,pageCon)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_GET_DEVICE_LIST, pageNum, pageCon);
        String strResult = new HttpTransport().getJSONfromURL(mAccessToken, url);
        SvrResponse<Data<Device>> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Data<Device>>>(){}.getType());

        if(result.getResponseCode().equals(ApiConstants.CODE_OK)) {

            return new DeviceApiResponse(result.getResponseCode(), result.getMessage(),
                    result.getData().getTotal(), result.getData().getPage(), result.getData().getRowNum(), result.getData().getRows());

        }else{

            return new DeviceApiResponse(result.getResponseCode(), result.getMessage(), null);
        }

    }

    public DeviceApiResponse deviceRegistration(String svcTgtSeq, String devNm, String spotDevId, String devPwd,
                                                String devModelNm, String termlMakrNm, String protId, String bindTypeCd, String mbrSeq,
                                                String mbrId, String rootGwCnctId, String type){
        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(svcTgtSeq, devNm, spotDevId, devPwd, devModelNm, termlMakrNm, protId,
                bindTypeCd, mbrSeq, mbrId, rootGwCnctId, type)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_POST_DEVICE_REG, svcTgtSeq);

        String requestJson = null;

        try {

            JSONObject requestObj = new JSONObject();
            requestObj.put("svcTgtSeq", svcTgtSeq);
            requestObj.put("spotDevId", spotDevId);
            requestObj.put("devNm", devNm);
            requestObj.put("devPwd", devPwd);
            requestObj.put("devModelNm", devModelNm);
            requestObj.put("termlMakrNm", termlMakrNm);
            requestObj.put("protId", protId);
            requestObj.put("bindTypeCd", bindTypeCd);
            requestObj.put("mbrSeq", mbrSeq);
            requestObj.put("mbrId", mbrId);
            requestObj.put("rootGwCnctId", rootGwCnctId);
            requestObj.put("type", type);

            requestJson = requestObj.toString();

            android.util.Log.d(TAG, "deviceRegistration requestJson = " + requestJson);

        }catch (Exception e){
            e.printStackTrace();
        }

        String strResult = new HttpTransport().postJsonToURL(mAccessToken, url, requestJson);
        SvrResponse<Device> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Device>>() {
        }.getType());

        ArrayList<Device> arrayResult = new ArrayList<>();
        arrayResult.add(result.getData());
        return new DeviceApiResponse(result.getResponseCode(), result.getMessage(), arrayResult);
    }

    public DeviceApiResponse deviceModify(String svcTgtSeq, String spotDevSeq, String devNm, String spotDevId, String devPwd,
                                          String devModelNm, String termlMakrNm, String mbrSeq,
                                          String mbrId, String gwCnctId, String useYn, String type){
        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(svcTgtSeq, spotDevSeq, devNm, spotDevId, devPwd, devModelNm, termlMakrNm, mbrSeq, mbrId, gwCnctId, useYn, type)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        String url = Utils.makeURL(ApiConstants.OPEN_API_POST_DEVICE_MODIFY, svcTgtSeq, spotDevSeq);

        String requestJson = null;

        try {

            JSONObject requestObj = new JSONObject();
            requestObj.put("svcTgtSeq", svcTgtSeq);
            requestObj.put("spotDevSeq", spotDevSeq);
            requestObj.put("spotDevId", spotDevId);
            requestObj.put("devNm", devNm);
            requestObj.put("devPwd", devPwd);
            requestObj.put("devModelNm", devModelNm);
            requestObj.put("termlMakrNm", termlMakrNm);
            requestObj.put("mbrSeq", mbrSeq);
            requestObj.put("mbrId", mbrId);
            requestObj.put("gwCnctId", gwCnctId);
            requestObj.put("useYn", useYn);
            requestObj.put("type", type);

            requestJson = requestObj.toString();

            android.util.Log.d(TAG, "deviceModify requestJson = " + requestJson);

        }catch (Exception e){
            e.printStackTrace();
        }

        String strResult = new HttpTransport().putJsonToURL(mAccessToken, url, requestJson);
        SvrResponse<Device> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Device>>() {
        }.getType());

        ArrayList<Device> arrayResult = new ArrayList<>();
        arrayResult.add(result.getData());
        return new DeviceApiResponse(result.getResponseCode(), result.getMessage(), arrayResult);
    }

    public DeviceApiResponse uploadDeviceImg(Device device, String filePath){

        //error handling - user access token missing
        checkAccessToken();

        //error handling - parameter missing
        if(!Utils.isValidParams(device.getSvcTgtSeq(), device.getSpotDevSeq(), filePath)){
            throw new ReqParamException(ReqParamException.DEFAULT_MSG);
        }

        File uploadFile = new File(filePath);

        if(!uploadFile.exists()){
            return new DeviceApiResponse(ApiConstants.CODE_NG, "file is not exist!! path = " + filePath, null);
        }else{
            Log.d(TAG, "file is exist!! path = " + filePath);
        }

        String url = "";

        if(TextUtils.isEmpty(device.getAtcFileSeq())) {                     //최초 등록시

            url = Utils.makeURL(ApiConstants.OPEN_API_POST_UPLOAD_IMAGE, device.getSvcTgtSeq(), device.getSpotDevSeq());

        }else{                                                              //기 등록된 이미지 수정시

            url = Utils.makeURL(ApiConstants.OPEN_API_POST_UPDATE_IMAGE, device.getSvcTgtSeq(), device.getSpotDevSeq(), device.getAtcFileSeq());
        }

        String strResult = new HttpTransport().uploadFile(mAccessToken, url, filePath);

        SvrResponse<Device> result = JsonHandler.fromJson(strResult, new TypeToken<SvrResponse<Device>>() {
        }.getType());

        return new DeviceApiResponse(result.getResponseCode(), result.getMessage(), null);
    }

    private void checkAccessToken(){
        if(mAccessToken == null || mAccessToken.equals("")){
            throw new AccesTokenNullException(AccesTokenNullException.DEFAULT_MSG);
        }
    }


}
