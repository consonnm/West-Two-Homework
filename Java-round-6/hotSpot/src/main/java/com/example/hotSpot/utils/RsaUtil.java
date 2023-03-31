package com.example.hotSpot.utils;


import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class RsaUtil {
    // 加密的方式
    private static final String KEY_RSA_TYPE = "RSA";
    // 转换 算法/反馈模式/填充方案    RSA/ECB/PKCS1Padding (1024, 2048)
    // 加密算法有：AES，DES，DESede(DES3)和RSA 四种
    // 模式有CBC(有向量模式)和ECB(无向量模式)，向量模式可以简单理解为偏移量，使用CBC模式需要定义一个IvParameterSpec对象
    private static final String KEY_RSA_TYPE_ALL = "RSA/ECB/PKCS1Padding";
    // JDK方式 RSA加密最大只有 1024位
    private static final int KEY_SIZE = 1024;
    // 模长
    private static final int ENCODE_PART_SIZE = KEY_SIZE / 8;
    // 公钥
    public static final String PUBLIC_KEY_NAME = "public";
    // 私钥
    public static final String PRIVATE_KEY_NAME = "private";
    
    public static Map<String, String> keyPairMap = new HashMap<String, String>();
    /**
     * 创建公钥和私钥
     *
     */
    public static Map<String, String> createRSAKeys() {

        // 生成公钥和私钥对——给予 RSA算法生成对象
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(KEY_RSA_TYPE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 使用给定的随机源(以及默认参数集)初始化特定密钥大小的密钥对生成器
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());

        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 返回对此密钥对的公钥组件的引用
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 返回对此密钥对的私钥组件的引用
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 得到公钥字符串和秘钥字符串
        String publicKeyValue = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
        String privateKeyValue = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());

        // 存入公钥和私钥
        keyPairMap.put(PUBLIC_KEY_NAME, publicKeyValue);
        keyPairMap.put(PRIVATE_KEY_NAME, privateKeyValue);
        return keyPairMap;
    }


    /**
     * 公钥加密
     * 描述：1字节 = 8 位，最大加密长度为 128 - 11 = 117 字节，不管多长数据，加密出来都是128字节的长度
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encryptByPublicKey(String str, String publicKey) {
        // base64 编码的公钥
        byte[] publicBytes = Base64.decodeBase64(publicKey);
        // 公钥加密——按照X509标准对其进行编码的密钥 复制数组的内容，以防随后进行修改。
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
        // 已经加密过的数据
        List<byte[]> encodedDataList = new LinkedList<byte[]>();

        // 最大加密长度 1024/8/8 - 11
        int maxEncodeSize = ENCODE_PART_SIZE - 11;
        // 所有加密的数据
        String encodeBase64Result = null;
        try {
            // 密钥工厂——用于将密钥（Key类型的不透明加密密钥）转换成密钥规范（底层密钥材料的透明表示）
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            // 根据提供的密钥规范（密钥材料）生成公钥对象
            PublicKey publicKeys = keyFactory.generatePublic(x509EncodedKeySpec);
            // 为加密和解密提供密码功能（所传递的参数：转换）
            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE_ALL);
            // 用密钥初始化此Cipher    ENCRYPT_MODE：用于将 Cipher初始化为解密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, publicKeys);

            byte[] strBytes = str.getBytes("utf-8");
            // 获取所有被加密数据长度
            int strLen = strBytes.length;
            // 如果明文长度大于 模长-11 则要分组加密
            for (int i = 0; i < strLen; i += maxEncodeSize) {
                int curPosition = strLen - i;
                int tempLen = curPosition;
                if (curPosition > maxEncodeSize) {
                    tempLen = maxEncodeSize;
                }
                // 待加密分段数据
                byte[] tempBytes = new byte[tempLen];
                System.arraycopy(strBytes, i, tempBytes, 0, tempLen);
                byte[] tempEncodedData = cipher.doFinal(tempBytes);
                encodedDataList.add(tempEncodedData);
            }

            // 加密次数
            int partLen = encodedDataList.size();
            // 所有加密的长度
            int allEncodedLen = partLen * ENCODE_PART_SIZE;
            // 存放所有 RSA分段加密数据
            byte[] encodeData = new byte[allEncodedLen];
            for (int i = 0; i < partLen; i++) {
                byte[] tempByteList = encodedDataList.get(i);
                System.arraycopy(tempByteList, 0, encodeData, i * ENCODE_PART_SIZE, ENCODE_PART_SIZE);
            }
            encodeBase64Result = Base64.encodeBase64String(encodeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeBase64Result;
    }
    /**
     * 私钥解密
     *
     * @param str        解密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decryptByPrivateKey(String str, String privateKey) {
        byte[] privateBytes = Base64.decodeBase64(privateKey);
        byte[] encodeStr = Base64.decodeBase64(str);

        // 要解密的数据长度
        int encodePartLen = encodeStr.length / ENCODE_PART_SIZE;
        List<byte[]> decodeListData = new LinkedList<byte[]>();
        // 所有解密的数据
        String decodeStrResult = null;

        // 私钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        try {
            // 密钥工厂——用于将密钥（Key类型的不透明加密密钥）转换成密钥规范（底层密钥材料的透明表示）
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            // 根据提供的密钥规范（密钥材料）生成私钥对象
            PrivateKey privateKeys = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            // 为加密和解密提供密码功能（所传递的参数：转换）
            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE_ALL);
            // 用密钥初始化此Cipher    ENCRYPT_MODE：用于将 Cipher初始化为解密模式的常量
            cipher.init(Cipher.DECRYPT_MODE, privateKeys);

            // 初始化所有被解密数据长度
            int allDecodeByteLen = 0;
            for (int i = 0; i < encodePartLen; i++) {
                // 待解密数据分段
                byte[] tempEncodedData = new byte[ENCODE_PART_SIZE];
                System.arraycopy(encodeStr, i * ENCODE_PART_SIZE, tempEncodedData, 0, ENCODE_PART_SIZE);
                byte[] decodePartData = cipher.doFinal(tempEncodedData);
                decodeListData.add(decodePartData);
                allDecodeByteLen += decodePartData.length;
            }

            byte[] decodeResultBytes = new byte[allDecodeByteLen];
            for (int i = 0, curPosition = 0; i < encodePartLen; i++) {
                byte[] tempStrBytes = decodeListData.get(i);
                int tempStrBytesLen = tempStrBytes.length;
                System.arraycopy(tempStrBytes, 0, decodeResultBytes, curPosition, tempStrBytesLen);
                curPosition += tempStrBytesLen;
            }
            decodeStrResult = new String(decodeResultBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeStrResult;
    }

}
