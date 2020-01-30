package com.mss.testlib.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.mss.testlib.utils.constants.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import retrofit2.Retrofit;

public class IdentificationGenerator {
    private TelephonyManager mTelephonyMgr;
    private int LENGHT = 34;
    private String compliteur = "0";
    private Context context;
    private String regid, msg;
    //private GoogleCloudMessaging gcm;
    private static final String TAG = "lintikooo";
    private SharedPreferencesManager sharedPreferencesManager;
    private SharedPreferencesManager sharedPreferencesManagerUUID;
    int permissionCheck;
    Retrofit mRetrofiteClient;
    String pwdEncrypted, imeiEncrypted;

    public IdentificationGenerator(Context context) {
        this.context = context;
        this.mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.sharedPreferencesManager = new SharedPreferencesManager(context);
    }

    public String getIMEI() {
        this.sharedPreferencesManagerUUID = new SharedPreferencesManager(context, Util.SHARED_NAME_UUID);
        if (sharedPreferencesManagerUUID.getString(Util.UUID) != null && !sharedPreferencesManagerUUID.getString(Util.UUID).equals("") && !sharedPreferencesManagerUUID.getString(Util.UUID).equals(Util.NOT_SAVED)) {
            return sharedPreferencesManagerUUID.getString(Util.UUID);
        } else {
            sharedPreferencesManagerUUID.putString(Util.UUID, UUID.randomUUID().toString());
            return sharedPreferencesManagerUUID.getString(Util.UUID);
        }
    }

    public String getIMEI(Activity activity) {
        this.sharedPreferencesManagerUUID = new SharedPreferencesManager(context, Util.SHARED_NAME_UUID);
        if (sharedPreferencesManagerUUID.getString(Util.UUID) != null && !sharedPreferencesManagerUUID.getString(Util.UUID).equals("") && !sharedPreferencesManagerUUID.getString(Util.UUID).equals(Util.NOT_SAVED)) {
            return sharedPreferencesManagerUUID.getString(Util.UUID);
        } else {
            sharedPreferencesManagerUUID.putString(Util.UUID, UUID.randomUUID().toString());
            return sharedPreferencesManagerUUID.getString(Util.UUID);
        }
    }

    public String getIdSession(Activity activity) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
        //String ICCID = this.mTelephonyMgr.getSimSerialNumber();
        Calendar calendar = Calendar.getInstance();
        Log.i("idSession", new SimpleDateFormat("yyyyMMddHHmmsss").format(calendar.getTime()).toString() + "12345");
        return new SimpleDateFormat("yyyyMMddHHmmsss").format(calendar.getTime()).toString() + "12345";
    }

    public String getIdSession() {
        Calendar calendar = Calendar.getInstance();
        return (new SimpleDateFormat("yyMMddHHmmss").format(calendar.getTime()).toString() + sharedPreferencesManager.getString(Util.MSISDN));
    }

    public String getIdSession(String MSISDN) {
        Calendar calendar = Calendar.getInstance();
        return (new SimpleDateFormat("yyMMddHHmmss").format(calendar.getTime()).toString() + MSISDN);
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
}
