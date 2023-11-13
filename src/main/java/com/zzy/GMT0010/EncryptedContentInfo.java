package com.zzy.GMT0010;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description  0010规范 9.1
 * @since 2023/9/12 17:23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncryptedContentInfo extends ASN1Object {

    private ASN1ObjectIdentifier contentType;  //内容类型
    private AlgorithmIdentifier contentEncryptionAlgorithmIdentifier;   //内容加密算法标识符
    private ASN1OctetString encryptedContent;   //加密内容

    public EncryptedContentInfo(ASN1Sequence instance) {
        this.contentType = ASN1ObjectIdentifier.getInstance(instance.getObjectAt(0));
        this.contentEncryptionAlgorithmIdentifier = AlgorithmIdentifier.getInstance(instance.getObjectAt(1));
        this.encryptedContent = ASN1OctetString.getInstance(instance.getObjectAt(2));
    }

    public static EncryptedContentInfo getInstance(Object objectAt) {
        if (objectAt instanceof EncryptedContentInfo) {
            return (EncryptedContentInfo) objectAt;
        } else {
            return new EncryptedContentInfo(ASN1Sequence.getInstance(objectAt));
        }
    }


    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(3);
        v.add(this.contentType);
        v.add(this.contentEncryptionAlgorithmIdentifier);
        v.add(this.encryptedContent);
        return new DERSequence(v);
    }
}
