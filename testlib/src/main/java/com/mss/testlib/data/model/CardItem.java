package com.mss.testlib.data.model;


import com.google.gson.annotations.SerializedName;

public class CardItem {

    @SerializedName("CarteType")
    private String mCardType;
    @SerializedName("Validity")
    private String mDate;
    @SerializedName("BankName")
    private String mBankName;
    private String mType;
    private String mPayType;
    @SerializedName("Statut")
    private String mStatus;
    @SerializedName("Message")
    private String mCause;

    public CardItem(String type, String date, String cardType, String bankName, String status) {
        mType = type;
        mCardType = cardType;
        mDate = date;
        mBankName = bankName;
        mStatus = status;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getCardType() {
        return mCardType;
    }

    public String getDate() {
        return mDate;
    }

    public String getBankName() {
        return mBankName;
    }

    public String getType() {
        return mType;
    }

    public void setCardType(String mCardType) {
        this.mCardType = mCardType;
    }

    public String getPayType() {
        return mPayType;
    }

    public void setPayType(String mPayType) {
        this.mPayType = mPayType;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setBankName(String mBankName) {
        this.mBankName = mBankName;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getCause() {
        return mCause;
    }

    public void setCause(String mCause) {
        this.mCause = mCause;
    }

    public class CardType {
        public static final String MOBICASH     = "Mobicash";
        public static final String OTHERBANK    = "Bancaire";
        public static final String ADDCARD      ="AddCard";
    }
    public class CardPayType {
        public static final String MOBICASH     = "P";
        public static final String OTHERBANK    = "S";
    }
    public class CardStatus {
        public static final String BLOQUE     = "card invalid";
        public static final String ACTIVE     = "Activé";
    }
}
