package com.af.asn1.GMT0019;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description  todo  临时使用 暂存biginteger
 * @since 2023/9/13 11:01
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SM2PrivateKey  implements ECPrivateKey {

    private BigInteger s;


    @Override
    public String getAlgorithm() {
        return "SM2";
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
    public ECParameterSpec getParams() {
        return null;
    }
}
