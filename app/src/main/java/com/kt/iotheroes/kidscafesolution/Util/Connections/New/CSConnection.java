package com.kt.iotheroes.kidscafesolution.Util.Connections.New;


import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kksd0900 on 16. 9. 29..
 */
public interface CSConnection {
    @GET("/user/management/select")
    Observable<Response<User>> getUser(@Query("id") String id);


}

