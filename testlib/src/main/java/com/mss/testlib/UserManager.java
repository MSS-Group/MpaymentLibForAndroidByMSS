package com.mss.testlib;

import android.content.Context;

import com.mss.testlib.dialog.LoginDialog;
import com.mss.testlib.dialog.OnLoginClickListener;

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
}
