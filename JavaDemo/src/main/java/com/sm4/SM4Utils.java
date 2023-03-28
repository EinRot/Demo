package com.sm4;

import lombok.SneakyThrows;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 国密SM4加解密
 */
public class SM4Utils {
    // 算法名称
    public static final String ALGORITHM_NAME = "SM4";
    // ECB P5填充
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    // CBC P5填充
    public static final String ALGORITHM_NAME_CBC_PADDING = "SM4/CBC/PKCS5Padding";
    // 密钥长度
    public static final int DEFAULT_KEY_SIZE = 128;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) {
        byte[] bytes2 = SM4Utils.generateKey();
        byte[] bytes = SM4Utils.encrypt_Ecb_Padding(bytes2,"aaa".getBytes());
        System.out.println(bytes);
        byte[] bytes1 = SM4Utils.decrypt_Ecb_Padding(bytes2, bytes);
        System.out.println(bytes1);
    }
    /**
     * 获取密钥
     *
     * @return byte
     */
    public static byte[] generateKey() {
        return generateKey(DEFAULT_KEY_SIZE);
    }

    /**
     * 获取指定长度密钥
     *
     * @param keySize 密钥的长度
     * @return 二进制字节数组
     */
    @SneakyThrows
    public static byte[] generateKey(int keySize) {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /**
     * ECB P5填充加密
     *
     * @param key  密钥
     * @param data 明文数据
     * @return byte
     */
    @SneakyThrows
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * ECB P5填充解密
     *
     * @param key        密钥
     * @param cipherText 加密后的数据
     * @return byte
     */
    @SneakyThrows
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    /**
     * CBC P5填充加密
     *
     * @param key  密钥
     * @param iv   偏移量
     * @param data 明文数据
     * @return byte
     */
    @SneakyThrows
    public static byte[] encrypt_Cbc_Padding(byte[] key, byte[] iv, byte[] data) {
        Cipher cipher = generateCbcCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(data);
    }

    /**
     * CBC P5填充解密
     *
     * @param key        密钥
     * @param iv         偏移量
     * @param cipherText 加密数据
     * @return byte
     */
    @SneakyThrows
    public static byte[] decrypt_Cbc_Padding(byte[] key, byte[] iv, byte[] cipherText) {
        Cipher cipher = generateCbcCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(cipherText);
    }

    /**
     * ECB P5填充加解密Cipher初始化
     *
     * @param algorithmName 算法名称
     * @param mode          1 加密 2解密
     * @param key           密钥
     * @return Cipher
     */
    @SneakyThrows
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * CBC P5填充加解密Cipher初始化
     *
     * @param algorithmName 算法名称
     * @param mode          1 加密 2解密
     * @param key           密钥
     * @param iv            偏移量
     * @return Cipher
     */
    @SneakyThrows
    private static Cipher generateCbcCipher(String algorithmName, int mode, byte[] key, byte[] iv) {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(mode, sm4Key, ivParameterSpec);
        return cipher;
    }
}
