package com.af.asn1.GMT0019;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/13 13:01
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SM2PublicKey implements ECPublicKey {
    private BigInteger x;
    private BigInteger y;



    @Override
    public ECPoint getQ() {
        X9ECParameters parameters = GMNamedCurves.getByName("sm2p256v1");
        ECParameterSpec ecParameterSpec =
                new ECParameterSpec(
                        parameters.getCurve(),
                        parameters.getG(),
                        parameters.getN(),
                        parameters.getH()
                );
        return ecParameterSpec.getCurve().createPoint(x, y);
    }

    @Override
    public String getAlgorithm() {
        return null;
    }

    @Override
    public String getFormat() {
        return null;
    }

    @Override
    public byte[] getEncoded() {
        return new byte[0];
    }

    @Override
    public ECParameterSpec getParameters() {
        return null;
    }
}
