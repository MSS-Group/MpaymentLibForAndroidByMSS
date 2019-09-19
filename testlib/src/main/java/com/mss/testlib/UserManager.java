package com.mss.testlib;

import android.content.Context;

import com.mss.testlib.dialog.LoginDialog;
import com.mss.testlib.dialog.OnLoginClickListener;
import com.mss.testlib.dialog.PaymentDialog;

public class UserManager {

    public static double getUserAmount(String username){
        if (username.equalsIgnoreCase("wassim"))
            return 3000;
        else if (username.equalsIgnoreCase("aymen"))
            return 2000;
        else
            return 0;
    }


    public static void showLoginDialog(Context context, OnLoginClickListener onLoginClickListener){
        LoginDialog loginDialog = new LoginDialog(context);
        loginDialog.setOnLoginClickListener(onLoginClickListener);
        loginDialog.show();
    }

    public static void showPaymentDialog(Context context,String idClient ,String idClientMobicash,String idMerchant ,String idMerchantMobicash,String idTransaction,String token,String amount, OnLoginClickListener onLoginClickListener){
        PaymentDialog loginDialog = new PaymentDialog(context,amount);
        loginDialog.setOnLoginClickListener(onLoginClickListener);
        loginDialog.show();
    }
}
