package com.zzy.GMT0016;


import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author zzypersonally@gmail.com
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
        //2. 生成公钥私钥对   此密钥对就是需要保护的密钥对
        //3. 用SK加密私钥 BC库SM4 ECB 加密
        //4.用已知的外部公钥加密SK
        //5.生成EnvelopedKeyBlob {@link EnvelopedKeyBlob}
        return null;
    }

}
