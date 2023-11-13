package com.zzy.GMT0010;

import com.af.asn1.GMT0010.fundamentalType.CertificateRevocationLists;
import com.af.asn1.GMT0010.fundamentalType.ExtendCertificateAndCertificate;
import com.af.asn1.GMT0010.fundamentalType.Version;
import lombok.*;
import org.bouncycastle.asn1.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/12 16:43
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SignedAndEnvelopedData extends ASN1Object {

    private Version version;  //版本
    private ASN1Set recipientInfos;   //接收者信息
    private ASN1Set digestAlgorithm;   //摘要算法标识符
    private EncryptedContentInfo encryptedContentInfo;   //加密内容信息
    private ExtendCertificateAndCertificate certificate;      //证书
    //证书吊销列表
    private CertificateRevocationLists crls;
    private ASN1Set signerInfos;     //签名者信息

    public SignedAndEnvelopedData(ASN1Sequence instance) {
        ASN1Encodable objectAt = instance.getObjectAt(0);
        this.version = Version.getInstance(objectAt);
        this.recipientInfos = ASN1Set.getInstance(instance.getObjectAt(1));
        this.digestAlgorithm = ASN1Set.getInstance(instance.getObjectAt(2));
        this.encryptedContentInfo = EncryptedContentInfo.getInstance(instance.getObjectAt(3));
        if (instance.size() > 4) {
            ASN1TaggedObject taggedObject = ASN1TaggedObject.getInstance(instance.getObjectAt(4));
            if (taggedObject.getTagNo() == 0) {
                this.certificate = ExtendCertificateAndCertificate.getInstance(taggedObject.getLoadedObject());
            } else if (taggedObject.getTagNo() == 1) {
                this.crls = CertificateRevocationLists.getInstance(taggedObject.getLoadedObject());
            }
        }
        this.signerInfos = ASN1Set.getInstance(instance.getObjectAt(instance.size() - 1));
    }

    public static SignedAndEnvelopedData getInstance(Object obj) {
        return new SignedAndEnvelopedData(ASN1Sequence.getInstance(obj));
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(7);
        v.add(this.version);
        v.add(this.recipientInfos);
        v.add(this.digestAlgorithm);
        v.add(this.encryptedContentInfo);
        if (this.certificate != null) {
            v.add(new DERTaggedObject(false, 0, this.certificate));
        }

        if (this.crls != null) {
            v.add(new DERTaggedObject(false, 1, this.crls));
        }
        v.add(this.signerInfos);

        return new DERSequence(v);
    }
}
