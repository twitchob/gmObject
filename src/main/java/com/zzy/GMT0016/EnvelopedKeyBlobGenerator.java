package com.zzy.GM0016;

import com.af.utils.SM2Util;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 用来生成 EnvelopedKeyBlob 的工具类
 * @since 2023/10/8 12:01
 */
public class EnvelopedKeyBlobGenerator {

    /**
     * generateEnvelopedKeyBlob
     * todo 生成EnvelopedKeyBlob 加密密钥对保护结构
     * 1. 生成对称密钥 SK  用来加密私钥 长度128bit 16字节
     * 2. 生成公钥私钥对   此密钥对就是需要保护的密钥对
     * 3. 用SK加密私钥    加密私钥的D值
     * 4.用已知的外部公钥加密SK
     * 5.生成EnvelopedKeyBlob {@link EnvelopedKeyBlob}
     */
    public static EnvelopedKeyBlob generateEnvelopedKeyBlob() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        //1. 生成对称密钥 SK  用来加密私钥 长度128bit 16字节
        byte[] SK = new byte[16];
        //2. 生成公钥私钥对   此密钥对就是需要保护的密钥对
        KeyPair keyPair = SM2Util.generateKeyPair();

        //获取私钥的D值
//        keyPair.getPrivate().


        //3. 用SK加密私钥 BC库SM4 ECB 加密
//        SM4Util.encrypt_ECB_Padding(SK, );

        //4.用已知的外部公钥加密SK
        //5.生成EnvelopedKeyBlob {@link EnvelopedKeyBlob}
        return null;
    }

}
