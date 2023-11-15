package com.zzy.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.ECKeyUtil;
import cn.hutool.crypto.SmUtil;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/11/14 18:28
 */
public class PrivateKeyUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    /**
     * BCECPrivateKey获取D值数组形式
     *
     * @param privateKey BCECPrivateKey
     * @return D值数组形式
     */
    public static byte[] getDFromBCECPrivateKey(BCECPrivateKey privateKey) {
        BigInteger d = privateKey.getD();
        return BigIntegerUtil.asUnsigned32ByteArray(d);
    }

    /**
     * 从D值获取BCECPrivateKey
     *
     * @param d D值 数组形式
     * @return BCECPrivateKey
     */
    public static BCECPrivateKey getBCECPrivateKeyFromD(byte[] d) throws Exception {
        ECPrivateKeyParameters sm2PrivateParams = ECKeyUtil.toSm2PrivateParams(d);
        ECParameterSpec ecParameterSpec = new ECParameterSpec(SmUtil.SM2_DOMAIN_PARAMS.getCurve(), SmUtil.SM2_DOMAIN_PARAMS.getG(), SmUtil.SM2_DOMAIN_PARAMS.getN(), SmUtil.SM2_DOMAIN_PARAMS.getH());
        ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(sm2PrivateParams.getD(), ecParameterSpec);
        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC"); // "EC" for ECC
        PrivateKey privateKey = keyFactory.generatePrivate(ecPrivateKeySpec);
        return (BCECPrivateKey) privateKey;
    }

    /**
     * 从0018私钥中获取D值
     * @param privateKey 0018私钥 4字节(256)+32字节D
     * @return  D值 数组形式
     */
    public static byte[] getDFrom0018PrivateKey(String privateKey) {
        byte[] D = new byte[32];
        byte[] decoded = Base64.decode(privateKey);
        System.arraycopy(decoded, 4, D, 0, 32);
        return D;
    }

    /**
     * 从D值获取0018私钥
     * @param D 32字节D 数组形式
     * @return 0018私钥 4字节(256)+32字节D
     */
    public static String get0018PrivateKeyFromD(byte[] D) {
        byte[] bytes = ArrayUtil.addAll(new byte[]{0x00, 0x01, 0x00, 0x00}, D);
        return Base64.encode(bytes);
    }

}
