package com.zzy.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ArrayUtil;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.Security;

/**
 * @author zzypersonally@gmail.com
 * @description 公钥格式有0018(256 4个字节 + 64字节X + 64字节Y, 前32字节为0) BCEC (04+32字节x+32字节Y)格式需要转换
 * @since 2023/11/14 17:36
 */
public class PublicKeyUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    /**
     * ECC 获取x y
     *
     * @param publicKey Base64编码的 ECC格式公钥(04+32字节x+32字节Y)
     * @return Base64编码的 32字节x+32字节Y
     */
    public static String getXYFromECC(String publicKey) {
        byte[] decode = Base64.decode(publicKey);
        byte[] x = ArrayUtil.sub(decode, 1, 33);
        byte[] y = ArrayUtil.sub(decode, 33, 65);
        byte[] bytes = ArrayUtil.addAll(x, y);
        return Base64.encode(bytes);
    }

    /**
     * X Y 获取ECC
     *
     * @param xy Base64编码的 32字节x+32字节Y
     * @return Base64编码的 ECC格式公钥(04+32字节x+32字节Y)
     */
    public static String getECCFromXY(String xy) {
        byte[] decode = Base64.decode(xy);
        byte[] x = ArrayUtil.sub(decode, 0, 32);
        byte[] y = ArrayUtil.sub(decode, 32, 64);
        byte[] bytes = ArrayUtil.addAll(new byte[]{0x04}, x, y);
        return Base64.encode(bytes);
    }


    /**
     * 0018 获取x y
     *
     * @param publicKey Base64编码的 0018格式公钥(4字节(256)+32字节X+32字节Y)
     * @return Base64编码的 32字节x+32字节Y
     */
    public static String getXYFrom0018(String publicKey) {
        byte[] decode = Base64.decode(publicKey);
        byte[] x = ArrayUtil.sub(decode, 4, 36);
        byte[] y = ArrayUtil.sub(decode, 36, 68);
        byte[] bytes = ArrayUtil.addAll(x, y);
        return Base64.encode(bytes);
    }


    /**
     * X Y 获取0018
     *
     * @param xy Base64编码的 32字节x+32字节Y
     * @return Base64编码的 0018格式公钥(4字节(256)+32字节X+32字节Y)
     */
    public static String get0018FromXY(String xy) {
        byte[] decode = Base64.decode(xy);
        byte[] x = ArrayUtil.sub(decode, 0, 32);
        byte[] y = ArrayUtil.sub(decode, 32, 64);
        byte[] bytes = ArrayUtil.addAll(new byte[]{0x00, 0x01, 0x00, 0x00}, x, y);
        return Base64.encode(bytes);
    }


    /**
     * BCECPublicKey 获取X Y
     * @param publicKey BCECPublicKey
     * @return Base64编码的 32字节x+32字节Y
     */
    public static String getXYFromBCECPublicKey(BCECPublicKey publicKey) {
        org.bouncycastle.math.ec.ECPoint q = publicKey.getQ();
        byte[] x = q.getXCoord().getEncoded();
        byte[] y = q.getYCoord().getEncoded();
        byte[] bytes = ArrayUtil.addAll(x, y);
        return Base64.encode(bytes);
    }

    /**
     * X Y 获取BCECPublicKey
     * @param xy Base64编码的 32字节x+32字节Y
     * @return BCECPublicKey
     */
    public static BCECPublicKey getBCECPublicKeyFromXY(String xy) throws Exception {
        byte[] decode = Base64.decode(xy);
        BigInteger x = BigIntegerUtil.toPositiveInteger(ArrayUtil.sub(decode, 0, 32));
        BigInteger y = BigIntegerUtil.toPositiveInteger(ArrayUtil.sub(decode, 32, 64));
        return getBCECPublicKeyFromXY(x, y);
    }

    /**
     * 通过 x y 获取BCECPublicKey
     *
     * @param x BigInteger
     * @param y BigInteger
     */
    public static BCECPublicKey getBCECPublicKeyFromXY(BigInteger x, BigInteger y) throws Exception {
        X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECParameterSpec ecDomainParameters = new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y), ecDomainParameters);
        KeyFactory keyFactory = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        return (BCECPublicKey) keyFactory.generatePublic(ecPublicKeySpec);
    }

}
