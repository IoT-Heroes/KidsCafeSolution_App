package com.kt.gigaiot_sdk.data;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class DeviceApiResponse extends Response {

    private int total;
    private int page;
    private int rowNum;
    private ArrayList<Device> devices;

    public DeviceApiResponse(String responseCode, String message, ArrayList<Device> devices) {
        super(responseCode, message);
        this.devices = devices;
    }

    public DeviceApiResponse(String responseCode, String message, int total, int page, int rowNum, ArrayList<Device> devices) {
        super(responseCode, message);
        this.total = total;
        this.page = page;
        this.rowNum = rowNum;
        this.devices = devices;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getRowNum() {
        return rowNum;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }
}
