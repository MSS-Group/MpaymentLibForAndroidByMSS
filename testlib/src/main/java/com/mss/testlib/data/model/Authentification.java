
package com.mss.testlib.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Authentification {


    @SerializedName("ResultCode")
    private String mResultCode;

    @SerializedName("LstUser")
    private List<LstUser> mLstUser;

    @SerializedName("LstCard1")
    private List<CardItem> mLstCard1;
    @SerializedName("LstCard2")
    private List<CardItem> mLstCard2;

    @SerializedName("IdTransaction")
    private String mIdTransaction;

    @SerializedName("IdSession")
    private String mIdSession;

    @SerializedName("TypeOfCard")
    private String mTypeOfCard;

    public List<LstUser> getLstUser() {
        return mLstUser;
    }

    public void setLstUser(List<LstUser> lstUser) {
        mLstUser = lstUser;
    }

    public String getResultCode() {
        return mResultCode;
    }

    public List<CardItem> getLstCard1() {
        return mLstCard1;
    }

    public void setLstCard1(List<CardItem> mLstCard1) {
        this.mLstCard1 = mLstCard1;
    }

    public List<CardItem> getLstCard2() {
        return mLstCard2;
    }

    public void setLstCard2(List<CardItem> mLstCard2) {
        this.mLstCard2 = mLstCard2;
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

    public String getTypeOfCard() {
        return mTypeOfCard;
    }

    public void setTypeOfCard(String mTypeOfCard) {
        this.mTypeOfCard = mTypeOfCard;
    }
}
