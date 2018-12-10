package com.kt.gigaiot_sdk.data;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class MemberApiResponse extends Response {

    private Member member;

    public MemberApiResponse(String responseCode, String message, Member member) {
        super(responseCode, message);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
