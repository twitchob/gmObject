package com.zzy.GMT0010.fundamentalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

//6.8
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeyEncryptionAlgorithmIdentifier extends ASN1Object {
    private AlgorithmIdentifier signerInfo;

    public static KeyEncryptionAlgorithmIdentifier getInstance(ASN1Encodable objectAt) {
        if (objectAt instanceof KeyEncryptionAlgorithmIdentifier) {
            return (KeyEncryptionAlgorithmIdentifier) objectAt;
        } else {
            DLSequence objectAt1 = (DLSequence) objectAt;
            return new KeyEncryptionAlgorithmIdentifier(AlgorithmIdentifier.getInstance(objectAt1.getObjectAt(0)));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(signerInfo);
        return new DERSequence(v);
    }
}
