package com.maps.search.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    /**
     * SHA256 Encrypt
     * */
    public static String encryptSHA256(String pw) {
        String sha = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(pw.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++) {
                byte tmpStrByte = byteData[i];
                sb.append(Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1));
            }
            sha = sb.toString();
        }catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            sha = null;
        }
        return sha;
    }
}
