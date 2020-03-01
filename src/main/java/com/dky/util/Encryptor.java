package com.dky.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author liwen
 * @since 2017-02-24
 */
public class Encryptor {

    private static Log log = LogFactory.getLog(Encryptor.class);
    private static String hexString = "0123456789abcdef";

    public static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((0xFF & bytes[i]) < 0x10)
                sb.append("0").append(Integer.toHexString(0xFF & bytes[i]));
            else
                sb.append(Integer.toHexString(0xFF & bytes[i]));
        }
        return sb.toString();
    }

    public static byte[] hex2byte(String hex) {
        byte[] ret = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            ret[i >> 1] = (byte) (hexString.indexOf(hex.charAt(i)) << 4 | hexString
                    .indexOf(hex.charAt(i + 1)));
        }
        return ret;
    }

    public static byte[] encodeDES(byte[] key, byte[] raw) {
        byte[] des = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); // 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretDesKey(key)); // 初始化
            des = cipher.doFinal(raw); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return des;
    }

    public static String encodeDES(String key, String raw) {
        byte[] des = encodeDES(key.getBytes(), raw.getBytes());
        return encodeBase64(des);
    }

    public static byte[] encode3DES(byte[] key, byte[] raw) {
        byte[] des = null;
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding"); // 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecret3DesKey(key)); // 初始化
            des = cipher.doFinal(raw); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return des;
    }

    public static String encode3DES(String key, String raw) {
        byte[] des = encode3DES(key.getBytes(), raw.getBytes());
        return encodeBase64(des);
    }

    public static byte[] encodeAES(byte[] key, byte[] raw) {
        byte[] aes = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key));
            SecretKey secretKey = kgen.generateKey();
            Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 初始化
            aes = cipher.doFinal(raw); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aes;
    }

    public static String encodeAES(String key, String raw) {
        byte[] aes = encodeAES(key.getBytes(), raw.getBytes());
        return encodeBase64(aes);
    }

    public static byte[] decodeDES(byte[] key, byte[] des) {
        byte[] raw = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getSecretDesKey(key)); // 初始化
            raw = cipher.doFinal(des); // 解密
        } catch (Exception e) {
            log.error(e);
        }
        return raw;
    }

    public static String decodeDES(String key, String des) {
        byte[] raw = decodeDES(key.getBytes(), decodeBase64(des));
        if (raw != null) {
            return new String(raw);
        }
        return null;
    }

    public static byte[] decode3DES(byte[] key, byte[] des) {
        byte[] raw = null;
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getSecret3DesKey(key)); // 初始化
            raw = cipher.doFinal(des); // 解密
        } catch (Exception e) {
            log.error(e);
        }
        return raw;
    }

    public static String decode3DES(String key, String des) {
        byte[] raw = decode3DES(key.getBytes(), decodeBase64(des));
        if (raw != null) {
            return new String(raw);
        }
        return null;
    }

    public static byte[] decodeAES(byte[] key, byte[] aes) {
        byte[] raw = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key));
            SecretKey secretKey = kgen.generateKey();
            Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKey); // 初始化
            raw = cipher.doFinal(aes); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return raw;
    }

    public static String decodeAES(String key, String aes) {
        byte[] raw = decodeAES(key.getBytes(), decodeBase64(aes));
        return byte2hex(raw);
    }

    private static SecretKeySpec getSecretDesKey(byte[] key) {
        byte[] byte8 = new byte[8];
        for (int i = 0; i < key.length && i < byte8.length; i++) {
            byte8[i] = key[i];
        }
        SecretKeySpec secretKey = new SecretKeySpec(byte8, "DES");
        return secretKey;
    }

    private static SecretKeySpec getSecret3DesKey(byte[] key) {
        byte[] byte24 = new byte[24];
        for (int i = 0; i < key.length && i < byte24.length; i++) {
            byte24[i] = key[i];
        }
        SecretKeySpec secretKey = new SecretKeySpec(byte24, "DESede");
        return secretKey;
    }

    public static String encodeBase64(byte[] raw) {
        return Base64.encode(raw);
    }

    public static byte[] decodeBase64(String base64) {
        return Base64.decode(base64);
    }

    public static byte[] MD5(byte[] raw) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException neverhappen) {
        }
        messageDigest.reset();
        messageDigest.update(raw);
        return messageDigest.digest();
    }

    public static String MD5(String raw) {
        return encodeBase64(MD5(raw.getBytes()));
    }

    public static byte[] sha1Hash(byte[] data) {
        byte[] bytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha-1");
            messageDigest.update(data);
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static String sha1Hash(String data) {
        return encodeBase64(sha1Hash(data.getBytes()));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(Encryptor.sha1Hash("123"));
    }
}
