package com.af.ca.GM.GMT0010;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 16:09
 */
@Getter
@AllArgsConstructor
public class EncryptedContentInfo extends ASN1Object {
    private final ASN1ObjectIdentifier contentType;
    private final AlgorithmIdentifier contentEncryptionAlgorithm;
    private ASN1OctetString encryptedContent;

    private EncryptedContentInfo(ASN1Sequence seq) {
        if (seq.size() < 2) {
            throw new IllegalArgumentException("Truncated Sequence Found");
        } else {
            this.contentType = (ASN1ObjectIdentifier)seq.getObjectAt(0);
            this.contentEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            if (seq.size() > 2) {
                this.encryptedContent = ASN1OctetString.getInstance((ASN1TaggedObject)seq.getObjectAt(2), false);
            }

        }
    }

    public static EncryptedContentInfo getInstance(Object obj) {
        if (obj instanceof EncryptedContentInfo) {
            return (EncryptedContentInfo)obj;
        } else {
            return obj != null ? new EncryptedContentInfo(ASN1Sequence.getInstance(obj)) : null;
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.contentType);
        v.add(this.contentEncryptionAlgorithm);
        if (this.encryptedContent != null) {
            v.add(new BERTaggedObject(false, 0, this.encryptedContent));
        }

        return new BERSequence(v);
    }

}
