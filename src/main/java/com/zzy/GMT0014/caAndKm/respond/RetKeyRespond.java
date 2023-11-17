package com.zzy.GMT0014.caAndKm.respond;

import com.zzy.GMT0010.SignedAndEnvelopedData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

/**
 * @author zzypersonally@gmail.com
 * @description   密钥响应格式包 处理申请密钥、恢复密钥申请时响应申请者
 * @since 2023/10/9 17:22
 */
@Getter
@AllArgsConstructor
public class RetKeyRespond extends ASN1Object {
    private ASN1Integer userCertNo;             //用户加密证书序列号，该项从CA申请包中取值
    private SubjectPublicKeyInfo retPubKey;     //返回给申请者的加密公钥数据
    private SignedAndEnvelopedData retPriKey;   //返回给申请者的加密私钥数据信封 对私钥做代签名的加密信封


    //getInstance
    public static RetKeyRespond getInstance(Object obj) {
        if (obj instanceof RetKeyRespond) {
            return (RetKeyRespond) obj;
        } else if (obj != null) {
            return new RetKeyRespond(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }

    //构造
    public RetKeyRespond(ASN1Sequence seq) {
        if (seq.size() != 3) {
            throw new IllegalArgumentException("wrong size for RetKeyRespond");
        } else {
            this.userCertNo = ASN1Integer.getInstance(seq.getObjectAt(0));
            this.retPubKey = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(1));
            this.retPriKey = SignedAndEnvelopedData.getInstance(seq.getObjectAt(2));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(3);
        v.add(userCertNo);
        v.add(retPubKey);
        v.add(retPriKey);
        return new DERSequence(v);
    }
}
