package com.zzy.GMT0010;

import com.af.asn1.GMT0010.fundamentalType.KeyEncryptionAlgorithmIdentifier;
import com.af.asn1.GMT0010.fundamentalType.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.IssuerAndSerialNumber;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/12 16:56
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipientInfo extends ASN1Object {

    private Version version;  //版本
    //颁发者和序列号
    private IssuerAndSerialNumber issuerAndSerialNumber;
    //用接收者公钥加密 数据加密密钥 的算法
    private KeyEncryptionAlgorithmIdentifier keyEncryptionAlgorithmIdentifier;
    //数据加密密钥密文SM2cipher
    private ASN1OctetString encryptedKey;

    public RecipientInfo(ASN1Sequence instance) {
        this.version = Version.getInstance(instance.getObjectAt(0));
        this.issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(instance.getObjectAt(1));
        this.keyEncryptionAlgorithmIdentifier = KeyEncryptionAlgorithmIdentifier.getInstance(instance.getObjectAt(2));
        this.encryptedKey = ASN1OctetString.getInstance(instance.getObjectAt(3));
    }

    public static RecipientInfo getInstance(Object objectAt) {
        if (objectAt instanceof RecipientInfo) {
            return (RecipientInfo) objectAt;
        } else {
            return new RecipientInfo(ASN1Sequence.getInstance(objectAt));
        }
    }


    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(4);
        v.add(this.version);
        v.add(this.issuerAndSerialNumber);
        v.add(this.keyEncryptionAlgorithmIdentifier);
        v.add(this.encryptedKey);
        return new DERSequence(v);
    }
}
