package com.zzy.GMT0010.fundamentalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

@Getter
@Setter
@AllArgsConstructor
//6.3 DigestAlgorithmsIdentifier
public class DigestAlgorithmIdentifier extends ASN1Object {
    AlgorithmIdentifier digestAlgorithmIdentifier;

    public static DigestAlgorithmIdentifier getInstance(Object objectAt) {
        if (objectAt instanceof DigestAlgorithmIdentifier) {
            return (DigestAlgorithmIdentifier) objectAt;
        } else {
            ASN1Encodable objectAt1 = ((DLSequence) objectAt).getObjectAt(0);
            return new DigestAlgorithmIdentifier(AlgorithmIdentifier.getInstance(objectAt1));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(digestAlgorithmIdentifier);
        return new DERSequence(v);
    }
}
