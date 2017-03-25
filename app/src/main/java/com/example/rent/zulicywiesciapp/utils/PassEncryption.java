package com.example.rent.zulicywiesciapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by md on 3/25/17.
 */

public class PassEncryption {

    public static String encryptString(String toEncrypt){
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            digest.update(toEncrypt.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
