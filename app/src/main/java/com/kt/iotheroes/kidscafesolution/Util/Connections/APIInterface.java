package com.kt.iotheroes.kidscafesolution.Util.Connections;

import com.kt.iotheroes.kidscafesolution.Model.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by mijeong on 2018. 12. 5..
 */

// RxJava는 Retrofit에서만 사용할 예정이다.
/*
  - 옵저버 패턴 : 옵저버가 발행(emit)을 하면 어떠한 데이터 흐름(stream)이 만들어지고 리시버가 구독(consume)하는 형태
  rxJava에서 데이터 발행 : Observable , 데이터 구독 : Observer (데이터의 흐름 속에서 데이터를 건져서 소비한다)
 Rx : 시간축이 존재하는 일련의 이벤트들을 Immutable한 List처럼 핸들링 할 수 있게 만들어줌
    subscribe하는 시점이 오기 전까지 observable은 아무런 데이터도 방출하지 않고 기다리기 때문
 */

/*
Retrofit을 Rx로 사용하는 이유
1. Model의 인자와 변수명이 다르거나 없어도 앱이 죽지 않는다.
2. Retrofit 통신에서 UI를 변화 시키는 도중 Acitivty가 사라지면서 발생하는 에러를 막을 수 있다.
3. 가독성이 좋아지고 유지보수가 편해진다
 */
public interface APIInterface {

    @FormUrlEncoded
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/user/management/insert")
    Observable<Response<User>> join(@Body User user);
}
