package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 5. 6..
 */
public class Data<T> {

    @SerializedName("total")
    int total;

    @SerializedName("page")
    int page;

    @SerializedName("rowNum")
    int rowNum;

    @SerializedName("rows")
    ArrayList<T> rows;

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getRowNum() {
        return rowNum;
    }

    public ArrayList<T> getRows() {
        return rows;
    }
}
