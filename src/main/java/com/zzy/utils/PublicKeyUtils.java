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

/**
 * @author zzypersonally@gmail.com
 * @description 公钥格式有0018(256 + 64字节X + 64字节Y, 前32字节为0) BCEC(04+32字节X+32字节Y) 格式需要转换
 * @since 2023/11/14 17:36
 */
public class PublicKeyUtils {


    /**
     * 0018公钥转  BCECPublicKey
     */
    public static BCECPublicKey publicKey0018ToBCECPublicKey(String publicKey) throws Exception {
        byte[] decode = Base64.decode(publicKey);
        BigInteger x = BigIntegerUtil.toPositiveInteger(ArrayUtil.sub(decode, 1, 33));
        BigInteger y = BigIntegerUtil.toPositiveInteger(ArrayUtil.sub(decode, 33, 65));
        return getBCECPublicKeyFromXY(x, y);
    }

    /**
     * BCECPublicKey 转0018公钥
     */
    public static String bcecPublicKeyTo0018(BCECPublicKey publicKey) throws Exception {
        org.bouncycastle.math.ec.ECPoint q = publicKey.getQ();
        byte[] x = q.getXCoord().getEncoded();
        byte[] y = q.getYCoord().getEncoded();
        byte[] bytes = ArrayUtil.addAll(new byte[]{0x04}, x, y);
        return Base64.encode(bytes);
    }

    /**
     * 通过x y 获取BCECPublicKey
     *
     * @param x 32字节
     * @param y 32字节
     */
    public static BCECPublicKey getBCECPublicKeyFromXY(BigInteger x, BigInteger y) throws Exception {
        X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECParameterSpec ecDomainParameters = new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y), ecDomainParameters);
        KeyFactory keyFactory = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        return (BCECPublicKey) keyFactory.generatePublic(ecPublicKeySpec);
    }


}
