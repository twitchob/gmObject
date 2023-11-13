package com.af.asn1.GMT0010.fundamentalType;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

//6.2 ContentEncryptionAlgorithmIdentifier
/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 0010 6.2
 * @since 2023/9/12 18:05
 */
public class ContentEncryptionAlgorithmIdentifier extends ASN1Object {
    AlgorithmIdentifier contentEncryptionAlgorithmIdentifier;

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(contentEncryptionAlgorithmIdentifier);
        return new DERSequence(v);
    }
}
