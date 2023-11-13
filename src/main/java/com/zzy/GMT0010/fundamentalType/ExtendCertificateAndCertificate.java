package com.af.asn1.GMT0010.fundamentalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//6.5 ExtendCertificateAndCertificate
public class ExtendCertificateAndCertificate extends ASN1Object {
    ASN1Set extendCertificateAndCertificate;


    public static ExtendCertificateAndCertificate getInstance(ASN1Encodable objectAt) {
        if (objectAt instanceof ExtendCertificateAndCertificate) {
            return (ExtendCertificateAndCertificate) objectAt;
        } else {
            ASN1Set asn1Set = ASN1Set.getInstance((DLTaggedObject)objectAt, false);
            return new ExtendCertificateAndCertificate(asn1Set);
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(extendCertificateAndCertificate);
        return new DERSequence(v);
    }
}
