
package com.mss.testlib.data.model;

//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

public class Activation {


    @SerializedName("ResultCode")
    private String mResultCode;

    @SerializedName("IdTransaction")
    private String mIdTransaction;

    @SerializedName("IdSession")
    private String mIdSession;


    public String getResultCode() {
        return mResultCode;
    }

    public void setResultCode(String resultCode) {
        mResultCode = resultCode;
    }

    public String getIdTransaction() {
        return mIdTransaction;
    }

    public void setIdTransaction(String IdTransaction) {
        mIdTransaction = IdTransaction;
    }

    public String getIdSession() {
        return mIdSession;
    }

    public void setIdSession(String IdSession) {
        mIdSession = IdSession;
    }
}
