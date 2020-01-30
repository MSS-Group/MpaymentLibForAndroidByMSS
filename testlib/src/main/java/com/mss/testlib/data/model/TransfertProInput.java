package com.mss.testlib.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransfertProInput {

    @SerializedName("MSISDN")
    @Expose
    private String mSISDN;
    @SerializedName("MSISDN_Receiver_Pro")
    @Expose
    private String mSISDNReceiverPro;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    @SerializedName("IdSession")
    @Expose
    private String idSession;
    @SerializedName("Pwd")
    @Expose
    private String pwd;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("receiverSrvc")
    @Expose
    private String receiverSrvc;
    @SerializedName("TypeOperation")
    @Expose
    private String typeOperation;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("Token")
    @Expose
    private String token;

    public TransfertProInput(String mSISDN, String mSISDNReceiverPro, String iMEI, String idSession, String pwd, String amount, String receiverSrvc, String typeOperation, String source, String token) {
        this.mSISDN = mSISDN;
        this.mSISDNReceiverPro = mSISDNReceiverPro;
        this.iMEI = iMEI;
        this.idSession = idSession;
        this.pwd = pwd;
        this.amount = amount;
        this.receiverSrvc = receiverSrvc;
        this.typeOperation = typeOperation;
        this.source = source;
        this.token = token;
    }

    public String getMSISDN() {
        return mSISDN;
    }

    public void setMSISDN(String mSISDN) {
        this.mSISDN = mSISDN;
    }

    public String getMSISDNReceiverPro() {
        return mSISDNReceiverPro;
    }

    public void setMSISDNReceiverPro(String mSISDNReceiverPro) {
        this.mSISDNReceiverPro = mSISDNReceiverPro;
    }

    public String getIMEI() {
        return iMEI;
    }

    public void setIMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceiverSrvc() {
        return receiverSrvc;
    }

    public void setReceiverSrvc(String receiverSrvc) {
        this.receiverSrvc = receiverSrvc;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
