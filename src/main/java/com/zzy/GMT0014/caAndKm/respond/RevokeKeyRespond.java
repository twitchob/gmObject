package com.af.ca.GM.GMT0014.caAndKm.respond;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;

/**
 * @author zzypersonally@gmail.com
 * @description    密钥撤销响应格式包 处理密钥撤销时响应申请者
 * @since 2023/10/10 12:02
 */
@Getter
@AllArgsConstructor
public class RevokeKeyRespond extends ASN1Object {

    private ASN1Integer userCertNo;     //用户加密证书序列号，取自申请包

    public static RevokeKeyRespond getInstance(ASN1TaggedObject o, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(o, explicit));
    }

    //getInstance
    public static RevokeKeyRespond getInstance(Object obj) {
        if (obj instanceof RevokeKeyRespond) {
            return (RevokeKeyRespond)obj;
        } else if (obj instanceof ASN1Integer) {
            return new RevokeKeyRespond((ASN1Integer)obj);
        } else if (obj != null) {
            return new RevokeKeyRespond(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }

    //构造
    public RevokeKeyRespond(ASN1Sequence seq) {
        if (seq.size() != 1) {
            throw new IllegalArgumentException("wrong size for CancelKeyRespond");
        } else {
            this.userCertNo = ASN1Integer.getInstance(seq.getObjectAt(0));
        }
    }



    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.userCertNo);
        return new DERSequence(v);
    }

}
