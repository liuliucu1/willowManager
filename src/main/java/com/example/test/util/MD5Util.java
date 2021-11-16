package com.example.test.util;

public class MD5Util {
    private static final String SALT = "ff00d601-8594-4056-ae60-637b6e4cb578";

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String passwordHash(String password) {
        return MD5(SALT + MD5(password) + SALT);
    }

}
