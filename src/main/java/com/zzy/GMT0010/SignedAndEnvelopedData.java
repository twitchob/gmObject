package com.af.ca.GM.GMT0010;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.asn1.*;

import java.util.Enumeration;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 16:08
 */
@Getter
@Setter
@AllArgsConstructor
public class SignedAndEnvelopedData extends ASN1Object {
    private ASN1Integer version;  //版本
    private ASN1Set recipientInfos;   //接收者信息
    private ASN1Set digestAlgorithms;   //摘要算法
    private EncryptedContentInfo encryptedContentInfo;   //加密内容信息
    private ASN1Set certificates;      //证书
    private ASN1Set crls;            //证书吊销列表
    private ASN1Set signerInfos;     //签名者信息

    public static SignedAndEnvelopedData getInstance(Object o) {
        if (o != null && !(o instanceof SignedAndEnvelopedData)) {
            if (o instanceof ASN1Sequence) {
                return new SignedAndEnvelopedData((ASN1Sequence) o);
            } else {
                throw new IllegalArgumentException("unknown object in factory:".concat(o.getClass().getName()));
            }
        } else {
            return (SignedAndEnvelopedData) o;
        }
    }

    public SignedAndEnvelopedData(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.version = (ASN1Integer) e.nextElement();
        this.recipientInfos = (ASN1Set) e.nextElement();
        this.digestAlgorithms = (ASN1Set) e.nextElement();
        this.encryptedContentInfo = EncryptedContentInfo.getInstance(e.nextElement());

        while (e.hasMoreElements()) {
            ASN1Object o = (ASN1Object) e.nextElement();
            if (o instanceof DLTaggedObject ) {
                DLTaggedObject tagged = (DLTaggedObject) o;
                switch (tagged.getTagNo()) {
                    case 0:
                        this.certificates = ASN1Set.getInstance(tagged, false);
                        break;
                    case 1:
                        this.crls = ASN1Set.getInstance(tagged, false);
                        break;
                    default:
                        throw new IllegalArgumentException("unknown tag value ".concat(String.valueOf(tagged.getTagNo())));
                }
            } else {
                this.signerInfos = (ASN1Set) o;
            }
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.version);
        v.add(this.recipientInfos);
        v.add(this.digestAlgorithms);
        v.add(this.encryptedContentInfo);
        if (this.certificates != null) {
            v.add(new DERTaggedObject(false, 0, this.certificates));
        }

        if (this.crls != null) {
            v.add(new DERTaggedObject(false, 1, this.crls));
        }

        v.add(this.signerInfos);
        return new DERSequence(v);
    }


}
