package com.kt.gigaiot_sdk.network;

/**
 * Created by NP1014425901 on 2015-03-12.
 */
public class ApiConstants {

    public static final String CODE_OK              = "OK";
    public static final String CODE_NG              = "NG";
    public static final String CODE_CONNECT_TIMEOUT = "9001";
    public static final String CODE_UNKNOWN         = "9009";

    public static final String MSG_CONNECT_TIMEOUT  = "ConnectTimeout";
    public static final String MSG_UNKNOWN          = "unknown error : ";

    //Open API 변경
    public static final String OPEN_API_AUTH_HOST_ADDRESS = "http://iotmakers.olleh.com";

    public static final String OPEN_API_HOST_ADDRESS = "http://iotmakers.olleh.com/api/v1";  //API-GW host

    public static final String OPEN_API_CORE_HOST = "http://iotmakers.olleh.com/coreapi/v1";   //API-GW event host

    public static final String OPEN_API_PUSH_HOST = "http://iotmakers.olleh.com/pushapi/v1";  //API-GW push host


    //OAuth API
    public static final String OPEN_API_POST_OAUTH_TOKEN = OPEN_API_AUTH_HOST_ADDRESS + "/oauth/token";

    //Device API
    public static final String OPEN_API_POST_DEVICE_REG = OPEN_API_HOST_ADDRESS + "/devices/%s";
    public static final String OPEN_API_POST_DEVICE_MODIFY = OPEN_API_HOST_ADDRESS + "/devices/%s/%s";
    public static final String OPEN_API_POST_UPLOAD_IMAGE = OPEN_API_HOST_ADDRESS + "/devices/%s/%s/image";
    public static final String OPEN_API_POST_UPDATE_IMAGE = OPEN_API_HOST_ADDRESS + "/devices/%s/%s/imageUpdate?atcFileSeq=%s";
    public static final String OPEN_API_GET_DEVICE_LIST = OPEN_API_HOST_ADDRESS + "/devices?offset=%s&limit=%s";

    //Protocol API
    public static final String OPEN_API_GET_PROTOCOLS = OPEN_API_HOST_ADDRESS + "/protocols/types";
    public static final String OPEN_API_GET_BINDTYPES = OPEN_API_HOST_ADDRESS + "/protocols/%s/bindtypes";
    public static final String OPEN_API_GET_ROOTGWCNCID = OPEN_API_HOST_ADDRESS + "/protocols/%s/%s/%s";

    //ServiceTarget API
    public static final String OPEN_API_GET_SVCTGT_SEQ_LIST  = OPEN_API_HOST_ADDRESS + "/svcTgt?mbrId=%s&pageNum=%s&pageCon=%s";

    //Tag Stream API
    public static final String OPEN_API_GET_TAGSTREAM_LIST  = OPEN_API_HOST_ADDRESS + "/streams/%s?pageNum=%s&pageCon=%s";
    public static final String OPEN_API_GET_TAGSTREAM_LOG = OPEN_API_HOST_ADDRESS + "/streams/%s/log?period=%s&count=%s";
    public static final String OPEN_API_GET_TAGSTREAM_LOG_LAST = OPEN_API_HOST_ADDRESS + "/streams/%s/%s/log/last";
    public static final String OPEN_API_POST_TAGSTREAM_CTRL  = OPEN_API_HOST_ADDRESS + "/devices/%s/%s/control/async";

    //Event API
    public static final String OPEN_API_GET_EVENT_LIST = OPEN_API_CORE_HOST + "/event/eventList?pageNum=1&pageCon=10&svcTgtSeq=%s&mbrId=%s";
    public static final String OPEN_API_GET_EVENT_LOG_LIST  = OPEN_API_CORE_HOST + "/event/logLastAccess/%s/%s/%s/%s";
    public static final String OPEN_API_GET_EVENT_LOG_LIST_TO_START  = OPEN_API_CORE_HOST + "/event/logLastAccess/%s/%s/%s/%s/%s";

    //Member API
    public static final String OPEN_API_GET_MEMBER_INFO = OPEN_API_HOST_ADDRESS + "/mbrs?mbrSeq=%s&pageNum=1&pageCon=10";

    //Push API
    public static final String OPEN_API_POST_PUSH_SESSION_REG = OPEN_API_PUSH_HOST + "/sessions";
    public static final String OPEN_API_POST_PUSH_SESSION_DEL = OPEN_API_PUSH_HOST + "/sessions/%s";


}
