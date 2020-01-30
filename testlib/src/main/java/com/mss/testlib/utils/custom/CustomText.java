package com.mss.testlib.utils.custom;

import android.content.Context;

import com.mss.testlib.R;

public class CustomText {

    private static CustomText instance;

    public CustomText() {
        instance = this;
    }

    public static CustomText getInstance() {
        return instance = new CustomText();
    }

    public String refSteg(String ref) {
        String f = ref.substring(0, 5) + " " + ref.substring(5, 8) + " " + ref.substring(8, 9);
        return f;
    }

    public String getMsisdnFormat(String msisdn) {
        return msisdn.substring(0, 2) + " " + msisdn.substring(2, 5) + " " + msisdn.substring(5, 8);
    }

    public String getDateHourHHmm(String date) {
        String d = date.substring(6, 8) + "-" + date.substring(4, 6) + "-" + date.substring(0, 4);
        String h = date.substring(8, 10) + ":" + date.substring(10, 12);
        return h + " / " + d;
    }

    public String getDate(String date) {
        if (date.length() >= 8)
            return date.substring(6, 8) + "-" + date.substring(4, 6) + "-" + date.substring(0, 4);
        else if (date.length() == 6)
            return date.substring(4, 6) + "-" + date.substring(2, 4) + "-" + date.substring(0, 2);
        else
            return date;
    }

    public String getHourHHmmSS(String date) {
        return date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
    }
    public String formatDate(String date) {
        return date.substring(0,2) + "/" + date.substring(2,4);
    }

    public String getMsisdnFormatDelete216(String msisdn) {
        String newString = msisdn.substring(3, 11);
        return newString.substring(0, 2) + " " + newString.substring(2, 5) + " " + newString.substring(5, 8);
    }

    public String getMsisdnFormatDelete216Crypted(String msisdn) {
        String newString1 = "❊❊❊❊";
        String newString2 = msisdn.substring(7, 11);
        String t = newString1 + newString2;
        String f = t.substring(0, 2) + " " + t.substring(2, 5) + " " + t.substring(5, 8);
        return f;
    }

    public String getCommissionAmount(Context context, String amount) {
        int amountInt = Integer.parseInt(amount);
        long amoutLongF = Math.round((amountInt * 1.785) / 100);
        String s = String.valueOf(amoutLongF);
        String f = s;
        if (s.length() == 1) {
            f = "000" + s;
        } else if (s.length() == 2) {
            f = "00" + s;
        } else if (s.length() == 3) {
            f = "0" + s;
        }
        String t = stringToAmountWithTND(context, f);
        return t;
    }

    public String millimeToDinar(String amount) {
        return String.valueOf(Integer.parseInt(amount) / 1000);
    }

    public String stringToAmountWithTND(Context context, String am) {
        String amount;
        if (am.contains(",") || am.contains("."))
            amount = am;
        else {
            double f = Double.parseDouble(am);
            //String s = String.format("%.2f", f);
            //Log.e("AMOUNNNNNNNNT ",s);
            amount = String.valueOf(f / 1000);
        }
        String amountToShow = "";
        if (amount.contains(".")) {
            int pointLength = amount.codePointCount(amount.indexOf("."), amount.length()) - 1;
            if (pointLength < 3)
                switch (pointLength) {
                    case 1:
                        amountToShow = amount + "00  " + context.getResources().getString(R.string.device);
                        amountToShow = amountToShow.replace(".", ",");
                        break;
                    case 2:
                        amountToShow = amount + "0 " + context.getResources().getString(R.string.device);
                        amountToShow = amountToShow.replace(".", ",");
                        break;
                }
            else
                amountToShow = amount.replace(".", ",") + " " + context.getResources().getString(R.string.device);
        } else if (amount.contains(",")) {
            int pointLength = amount.codePointCount(amount.indexOf(","), amount.length()) - 1;
            if (pointLength < 3)
                switch (pointLength) {
                    case 1:
                        amountToShow = amount + "00 " + context.getResources().getString(R.string.device);
                        break;
                    case 2:
                        amountToShow = amount + "0 " + context.getResources().getString(R.string.device);
                        break;
                }
            else amountToShow = amount + " " + context.getResources().getString(R.string.device);
        } else {
            amountToShow = amount + ",000  " + context.getResources().getString(R.string.device);
        }

        return amountToShow;
    }

    public String amountDinarWithTND(Context context, String amount) {

        String amountToShow = "";
        if (amount.contains(".")) {
            int pointLength = amount.codePointCount(amount.indexOf("."), amount.length()) - 1;
            if (pointLength < 3)
                switch (pointLength) {
                    case 1:
                        amountToShow = amount + "00  " + context.getResources().getString(R.string.device);
                        amountToShow = amountToShow.replace(".", ",");
                        break;
                    case 2:
                        amountToShow = amount + "0 " + context.getResources().getString(R.string.device);
                        amountToShow = amountToShow.replace(".", ",");
                        break;
                }
            else
                amountToShow = amount.replace(".", ",") + " " + context.getResources().getString(R.string.device);
        } else if (amount.contains(",")) {
            int pointLength = amount.codePointCount(amount.indexOf(","), amount.length()) - 1;
            if (pointLength < 3)
                switch (pointLength) {
                    case 1:
                        amountToShow = amount + "00 " + context.getResources().getString(R.string.device);
                        break;
                    case 2:
                        amountToShow = amount + "0 " + context.getResources().getString(R.string.device);
                        break;
                }
            else amountToShow = amount + " " + context.getResources().getString(R.string.device);
        } else {
            amountToShow = amount + ",000  " + context.getResources().getString(R.string.device);
        }

        return amountToShow;
    }

    public String verifcationTelNumWith216(String msisdn) {

        String msisdnWithOtSpace = msisdn.replace(" ", "");
        if (msisdnWithOtSpace.length() == 11 && (msisdnWithOtSpace.startsWith("216") || msisdnWithOtSpace.startsWith("+216") || msisdnWithOtSpace.startsWith("00216"))) {
            return getMsisdnFormatDelete216(msisdnWithOtSpace);
        } else if (msisdnWithOtSpace.length() == 8) {
            return msisdn;
        } else return null;
    }
}