package com.mss.testlib;

import android.content.Context;

import com.mss.testlib.dialog.LoginDialog;
import com.mss.testlib.dialog.PaymentCallback;
import com.mss.testlib.dialog.PaymentDialog;

public class UserManager {

    public static double getUserAmount(String username) {
        if (username.equalsIgnoreCase("wassim"))
            return 3000;
        else if (username.equalsIgnoreCase("aymen"))
            return 2000;
        else
            return 0;
    }


    public static void showLoginDialog(Context context, String idClient, String idClientMobicash, String idMerchant, String idMerchantMobicash, String idTransaction, String idVersion, String token, String amount, PaymentCallback paymentCallback) {
        LoginDialog loginDialog = new LoginDialog(context,
                idClient,
                idClientMobicash,
                idMerchant,
                idMerchantMobicash,
                idTransaction,
                idVersion,
                token,
                amount);
        loginDialog.setPaymentCallback(paymentCallback);
        loginDialog.show();
    }

    public static void showPaymentDialog(Context context, String msisdn, String idClient, String idClientMobicash, String idMerchant, String idMerchantMobicash, String idTransaction, String idVersion, String token, String amount, PaymentCallback paymentCallback) {
        PaymentDialog loginDialog = new PaymentDialog(context, msisdn, idMerchantMobicash, amount);
        loginDialog.setPaymentCallback(paymentCallback);
        loginDialog.show();
    }
}
