package com.zzy.GMT0010.fundamentalType;

import org.bouncycastle.asn1.*;

//6.1
/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 0010 6.1
 * @since 2023/9/12 18:05
 */
public class CertificateRevocationLists extends ASN1Object {
    ASN1Set certificateRevocationList;

    public CertificateRevocationLists(ASN1Set instance) {
        this.certificateRevocationList = instance;
    }

    public static CertificateRevocationLists getInstance(Object loadedObject) {
        if (loadedObject instanceof CertificateRevocationLists) {
            return (CertificateRevocationLists) loadedObject;
        } else {
            return new CertificateRevocationLists(ASN1Set.getInstance(loadedObject));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(this.certificateRevocationList);
        return new DERSequence(v);
    }
}
