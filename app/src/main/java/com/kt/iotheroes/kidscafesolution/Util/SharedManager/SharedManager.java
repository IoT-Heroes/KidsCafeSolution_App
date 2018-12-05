package com.kt.iotheroes.kidscafesolution.Util.SharedManager;

import com.kt.iotheroes.kidscafesolution.Model.User;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class SharedManager {
    /*
    volatile : memory에서만 read/write 연산이 일어남. 변수의 가시성 문제 해결
     */
    private volatile static SharedManager single;

    private User user;

    public User getUser() {
        return user;
    }

    public boolean setUser(User user) {
        try {
            this.user = user;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static SharedManager getInstance() {
        if (single == null) {
            // static method 동기화는 SharedManager 객체를 기준으로 이루어짐
            // JVM 안에 클래스 객체는 클래스 당 하나만 존재할 수 있으므로, 같은 클래스에 대해서는 오직 한 쓰레드만 동기화된 스태틱 메소드를 실행할 수 있다.
            synchronized (SharedManager.class) {
                if (single == null)
                    single = new SharedManager();
            }
        }
        return single;
    }
}
