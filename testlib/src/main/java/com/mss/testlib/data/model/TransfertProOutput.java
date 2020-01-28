package com.mss.testlib.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransfertProOutput {

    @SerializedName("ResultCode")
    @Expose
    private String resultCode;
    @SerializedName("idSession")
    @Expose
    private String idSession;
    @SerializedName("AuthorisationCode")
    @Expose
    private String authorisationCode;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getAuthorisationCode() {
        return authorisationCode;
    }

    public void setAuthorisationCode(String authorisationCode) {
        this.authorisationCode = authorisationCode;
    }

}
