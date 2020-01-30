package com.mss.testlib.dialog;

public interface PaymentCallback {

    //void login(String username,String password);

    void payment(int resultCode,String message);
}
