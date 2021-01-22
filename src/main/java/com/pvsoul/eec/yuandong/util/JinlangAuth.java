package com.pvsoul.eec.yuandong.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class JinlangAuth {

    private static final String CHARSET = "UTF-8";

    private static final String RSA_ALGORITHM = "RSA";

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq8BLTovUoLSQG6rkI64MfFiTAlgbVrbNo8R4GzIJhsoC2-Jt9nDvXgyfjZojWkHtZLyqJ8-pybZ22fkQQEe6dSf2OoDIzGcY40lgpUF9G0yRL7ik5fQ6vh7J5zVYUwiMd__vPD6IrIK36Ay36cfhjZBgy2Mwv80MbIgTuTx8gqQvHgUjkEn0MWOcF5hydE8XaprJavU6guC-T4Vy677WvYdv9uBNBn3lFd--JbG6Ngy9bO38gAFwhZSa0Bc_TerB5rinRXONyXQEKMrwQnIEhQoqRhCCa4Z9Iszn6bS-0pqbm2KuVWvArYDsxjl0opjItpGoZeqgMI_Vo9zhjH_4NQIDAQAB";

    public static boolean checkAuth(String verb,
                                    String contentType,
                                    String date,
                                    String canonicalizedResource,
                                    String authorization) throws ParseException {
/*
        Calendar before15Min = Calendar.getInstance(Locale.US);
        before15Min.add(Calendar.MINUTE, -15);
        Calendar after15Min = Calendar.getInstance();
        after15Min.add(Calendar.MINUTE, 15);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        Calendar signTime = Calendar.getInstance();
        signTime.setTime(sdf.parse(date));
        signTime.setTimeZone(TimeZone.getDefault());
        //数据签名时间超过系统时间正负15分钟的认为无效
        if (signTime.before(before15Min) || signTime.after(after15Min)) {
            return false;
        }
*/
        String data = verb + "\n" + contentType + "\n" + date + "\n" + canonicalizedResource;
        String contentMd5 = getDigest(data);
        String sign = publicDecrypt(authorization);
        return contentMd5.equals(sign);
    }

    /**
     * 公钥解密
     *
     * @param data 密文
     * @return 解密后的内容
     */

    public static String publicDecrypt(String data) {
        try {
            RSAPublicKey publicKey = getPublicKey(PUBLIC_KEY);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws NoSuchAlgorithmException, InvalidKeySpecException
     */
    private static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        return (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        return out.toByteArray();
    }

    /**
     * 1.先计算MD5 加密的二进制数组（128 位）。
     * 2. 再对这个二进制进行base64 编码（而不是对32 位字符串编码）
     * @param plainText 加密明文
     * @return 加密密文
     */
    private static String getDigest(String plainText) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            result = Base64.encodeBase64String(b);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密时发生异常", e);
        }
        return result;
    }

    /**
     * Description: 获取GMT 时间
     * @return 将当前时间转换为GMT 时区后的String
     */
    private static String getGMTTime(){
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        return sdf.format(cd.getTime());
    }
}
