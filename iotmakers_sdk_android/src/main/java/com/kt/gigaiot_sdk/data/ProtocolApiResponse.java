package com.kt.gigaiot_sdk.data;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class ProtocolApiResponse extends Response {

    private ArrayList<Protocol> protocols;

    private ArrayList<BindType> bindTypes;

    private ArrayList<RootGw> rootGws;

    public ProtocolApiResponse(String responseCode, String message, ArrayList<Protocol> protocols) {
        super(responseCode, message);
        this.protocols = protocols;
    }

    public ProtocolApiResponse(String responseCode, String message, ArrayList<Protocol> protocols, ArrayList<BindType> bindTypes) {
        super(responseCode, message);
        this.protocols = protocols;
        this.bindTypes = bindTypes;
    }

    public ProtocolApiResponse(String responseCode, String message, ArrayList<Protocol> protocols, ArrayList<BindType> bindTypes, ArrayList<RootGw> rootGws) {
        super(responseCode, message);
        this.protocols = protocols;
        this.bindTypes = bindTypes;
        this.rootGws = rootGws;
    }

    public ArrayList<Protocol> getProtocols() {
        return protocols;
    }

    public ArrayList<BindType> getBindTypes() {
        return bindTypes;
    }

    public ArrayList<RootGw> getRootGws() {
        return rootGws;
    }
}
