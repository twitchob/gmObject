package com.zzy.GMT0010.fundamentalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 0010 6.4
 * @since 2023/9/12 18:05
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DigestEncryptionAlgorithmIdentifier extends ASN1Object {
    private AlgorithmIdentifier digestEncryptionAlgorithmIdentifier;

    public static DigestEncryptionAlgorithmIdentifier getInstance(Object objectAt) {
        if (objectAt instanceof DigestEncryptionAlgorithmIdentifier) {
            return (DigestEncryptionAlgorithmIdentifier) objectAt;
        } else {
            DLSequence objectAt1 = (DLSequence) objectAt;
            return new DigestEncryptionAlgorithmIdentifier(AlgorithmIdentifier.getInstance(objectAt1.getObjectAt(0)));
        }

    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(digestEncryptionAlgorithmIdentifier);
        return new DERSequence(v);
    }
}
