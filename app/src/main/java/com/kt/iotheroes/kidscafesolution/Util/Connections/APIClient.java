package com.kt.iotheroes.kidscafesolution.Util.Connections;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class APIClient {

    private static Retrofit retrofit = null;
    // TODO : url넣기
    private static final String url = null;

    // getClient는 Retrofit 인터페이스를 설정할 때 마다 호출 됨
    public static APIInterface getClient() {
        // 네트워크 요청/응답을 로그에 표시하는 Interceptor 객체를 생성
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 네트워크 통신에 사용할 클라이언트 객체를 생성
        OkHttpClient client = new OkHttpClient.Builder()
                // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시
                .addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                // 통신 서버 주소 입력
                .baseUrl(url)
                // 서버에서 json 형식으로 데이터를 보내고 이를 파싱해서 받아옴
                .addConverterFactory(GsonConverterFactory.create())
                // 받은 응답을 observable 형태로 변환
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                // 네트워크 요청 로그를 표
                .client(client)
                .build();

        return retrofit.create(APIInterface.class);
    }
}
