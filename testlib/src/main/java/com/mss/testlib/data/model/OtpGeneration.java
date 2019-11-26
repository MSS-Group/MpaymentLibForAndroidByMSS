package com.mss.testlib.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpGeneration {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("idTransaction")
    @Expose
    private String idTransaction;
    @SerializedName("idSession")
    @Expose
    private String idSession;
    @SerializedName("language")
    @Expose
    private String language;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
