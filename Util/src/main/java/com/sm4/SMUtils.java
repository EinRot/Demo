package com.sm4;

import lombok.SneakyThrows;
import org.bouncycastle.util.encoders.Hex;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Map;

public class SMUtils {

    public void saobing(){
        System.out.println(SMUtils.sm4_ecb_decrypt("e9664b2bebccb6fe80da086044608115", "21bd9690dac2559a0b45059365ec1dabee8d88b903d334f33438496c265eeeee74dbc2ae7959aaf8c6f6eacb04702b1e7c43f6e1987df32510279f5164d9da22d102b80538acc2346f490fcdefa1ff9dde92e5c5e369134826202d27fae27780c62e7aac6e1fa8f1afeaef8403fbd7245b724a88dc1cf34d6b63d5fcfe0291c30e68c593f62e7f9df7b8540e053ad37962496d1d1296f9b8a03d95a01c4be95969e5a3a3d0882916e932452692bb01c1992fcf1f56c441e5786404f35b531033227890d551bf06c3084bf5e5129da1a754de50ac6ab597ef9e10976ab7446ddf3092d4cb44bae70c12d4d432842e1d82689c9a171231f4e93744e486d96f3aa12d780635b2ac16119e959bc099e296020bdff6996beb6e741ceeadc9908575c3"));
        System.out.println(SMUtils.sm4_ecb_encrypt("e9664b2bebccb6fe80da086044608115","techstar"));
        System.out.println(SMUtils.sm4_ecb_encrypt("e9664b2bebccb6fe80da086044608115","Techstar@2021"));
    }

    /**
     * 获取sm4的秘钥
     *
     * @return sm4秘钥
     */
    public static String generateSm4Key() {
        byte[] sm4key = SM4Utils.generateKey();
        return SecurityUtils.bytesToHexString(sm4key);
    }

    /**
     * sm4 Ecb加密
     *
     * @param key   加密密钥
     * @param input 加密内容
     * @return 加密结果
     */
    public static String sm4_ecb_encrypt(String key, String input) {
        byte[] cipherText = SM4Utils.encrypt_Ecb_Padding(SecurityUtils.hexStringToBytes(key), input.getBytes());
        String cipherTextString = SecurityUtils.bytesToHexString(cipherText);
        return cipherTextString;
    }

    /**
     * sm4 Ecb解密
     *
     * @param key        密钥
     * @param cipherText 解密内容
     * @return 解密结果
     */
    public static String sm4_ecb_decrypt(String key, String cipherText) {
        byte[] decryptedData = SM4Utils.decrypt_Ecb_Padding(SecurityUtils.hexStringToBytes(key),
                SecurityUtils.hexStringToBytes(cipherText));
        return new String(decryptedData);
    }

    /**
     * SM4 CBC 加密
     *
     * @param key   秘钥
     * @param iv    iv偏移量密钥
     * @param input 原始数据
     * @return 加密结果
     */
    public static String sm4_cbc_encrypt(String key, String iv, String input) {
        byte[] cipherText = SM4Utils.encrypt_Cbc_Padding(SecurityUtils.hexStringToBytes(key), SecurityUtils.hexStringToBytes(iv),
                input.getBytes());
        String cipherTextString = SecurityUtils.bytesToHexString(cipherText);
        return cipherTextString;
    }

    /**
     * SM4 Cbc解密
     *
     * @param key        秘钥
     * @param iv         iv偏移量密钥
     * @param cipherText 加密后的数据
     * @return 解密结果
     */
    public static String sm4_cbc_decrypt(String key, String iv, String cipherText) {
        byte[] decryptedData = SM4Utils.decrypt_Cbc_Padding(SecurityUtils.hexStringToBytes(key),
                SecurityUtils.hexStringToBytes(iv), SecurityUtils.hexStringToBytes(cipherText));
        return new String(decryptedData);
    }

}
