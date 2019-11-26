package com.mss.testlib.utils.encrypt;
import android.util.Log;


import com.mss.testlib.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Firas on 03/02/2016.
 */
public class RSA {

    public static String Decrypt(String encodedString) throws IllegalBlockSizeException, UnsupportedEncodingException,
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException
    {
        //le modulus n, un grand nombre entier qui est égal au produit de deux grands nombres premiers p et q,.
        byte[] modulusBytes = Base64.decode(BuildConfig.modulusBytes);
        //d*e mod z = 1
        byte[] DBytes = Base64.decode(BuildConfig.DBytes);
        BigInteger modulus = new BigInteger(1, modulusBytes );
        BigInteger exponent = new BigInteger(1, DBytes);

        RSAPrivateKeySpec rsaPrivKey = new RSAPrivateKeySpec(modulus, exponent);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PrivateKey privKey = fact.generatePrivate(rsaPrivKey);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.DECRYPT_MODE, privKey);


        byte[] cipherData = Base64.decode(encodedString);
        byte[] plainBytes = cipher.doFinal(cipherData);

        return new String(plainBytes, "UTF-8");
    }

    public static String Encrypt(String encodedString) throws IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException
    {
        //le modulus n, un grand nombre entier qui est égal au produit de deux grands nombres premiers p et q,.
        byte[] modulusBytes = Base64.decode(BuildConfig.modulusBytes);
        //L'exposant e.
        byte[] exponentBytes = Base64.decode(BuildConfig.exponentBytes);
        BigInteger modulus = new BigInteger(1, modulusBytes );
        BigInteger exponent = new BigInteger(1, exponentBytes);

        RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PublicKey pubKey = fact.generatePublic(rsaPubKey);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] plainBytes = new String(encodedString).getBytes("UTF-8");
        byte[] cipherData = cipher.doFinal( plainBytes );
        String encryptedString = Base64.encode(cipherData);
        return encryptedString;


    }


    public static String DecryptQRCode(String encodedString) throws IllegalBlockSizeException, UnsupportedEncodingException,
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException
    {
        //le modulus n, un grand nombre entier qui est égal au produit de deux grands nombres premiers p et q,.
        byte[] modulusBytes = Base64.decode(BuildConfig.exponentBytesQrCode);
        //d*e mod z = 1
        byte[] DBytes = Base64.decode(BuildConfig.DBytesQrCode);
        BigInteger modulus = new BigInteger(1, modulusBytes );
        BigInteger exponent = new BigInteger(1, DBytes);

        RSAPrivateKeySpec rsaPrivKey = new RSAPrivateKeySpec(modulus, exponent);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PrivateKey privKey = fact.generatePrivate(rsaPrivKey);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] plainBytes = new byte[0];
        try {
            byte[] cipherData = Base64.decode(encodedString);
            plainBytes = cipher.doFinal(cipherData);
        } catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
            //HomeLibActivity.getInstance().showErrorToast(HomeLibActivity.getInstance().getResources().getString(R.string.incorrect_data));
            //Toast.makeText(MainActivity.getInstance(), "incorrect data", Toast.LENGTH_LONG).show();
            Log.e("QrCodeExeption","incorrect data");

        }


        return new String(plainBytes, "UTF-8");
    }

    public static String EncryptQRCode(String encodedString) throws IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException
    {
        //le modulus n, un grand nombre entier qui est égal au produit de deux grands nombres premiers p et q,.
        byte[] modulusBytes = Base64.decode(BuildConfig.modulusBytesQrCode);
        //L'exposant e.
        byte[] exponentBytes = Base64.decode(BuildConfig.exponentBytesQrCode);
        BigInteger modulus = new BigInteger(1, modulusBytes );
        BigInteger exponent = new BigInteger(1, exponentBytes);

        RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PublicKey pubKey = fact.generatePublic(rsaPubKey);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] plainBytes = new String(encodedString).getBytes("UTF-8");
        byte[] cipherData = cipher.doFinal( plainBytes );
        String encryptedString = Base64.encode(cipherData);
        return encryptedString;


    }
}
