package com.zzy.GMT0010.fundamentalType;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

//6.10  ContentInfo

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 0010 6.10
 * @since 2023/9/12 18:05
 */
public class ContentInfo extends ASN1Object {
    private AlgorithmIdentifier contentType;
    private ASN1Encodable content;

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(contentType);
        v.add(content);
        return new DERSequence(v);
    }
}
