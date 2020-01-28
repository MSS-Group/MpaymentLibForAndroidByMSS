
package com.mss.testlib.data.model;

//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

public class PreActivation {

    @SerializedName("IdTransaction")
    private String mIdTransaction;
    @SerializedName("ResultCode")
    private String mResultCode;
    @SerializedName("IdSession")
    private String mIdSession;

    public String getIdTransaction() {
        return mIdTransaction;
    }

    public void setIdTransaction(String IdTransaction) {
        mIdTransaction = IdTransaction;
    }

    public String getResultCode() {
        return mResultCode;
    }

    public void setResultCode(String resultCode) {
        mResultCode = resultCode;
    }

    public String getIdSession() {
        return mIdSession;
    }

    public void setIdSession(String IdSession) {
        mIdSession = IdSession;
    }

}
