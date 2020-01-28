
package com.mss.testlib.data.model;

import com.google.gson.annotations.SerializedName;

public class LstUser {

    @SerializedName("idCommercant")
    private String mIdCommercant;
    @SerializedName("nom")
    private String mNom;

    public String getIdCommercant() {
        return mIdCommercant;
    }

    public void setIdCommercant(String idCommercant) {
        mIdCommercant = idCommercant;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

}
