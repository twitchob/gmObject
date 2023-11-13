package com.zzy.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;

import java.math.BigInteger;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 16:34
 */
@Getter
@AllArgsConstructor
public class RevokeKeyReq extends ASN1Object {
    private ASN1Integer userCertNo;       //指定用户证书序列号


    public RevokeKeyReq(BigInteger userCertNo) {
        this.userCertNo = new ASN1Integer(userCertNo);
    }

    public static RevokeKeyReq getInstance(Object object) {
        if (object instanceof RevokeKeyReq) {
            return (RevokeKeyReq) object;
        } else if (object != null) {
            ASN1EncodableVector vectore = new ASN1EncodableVector();
            if (object instanceof ASN1Integer) {
                vectore.add((ASN1Integer)object);
                object = new DERSequence(vectore);
            }
            return new RevokeKeyReq(ASN1Sequence.getInstance(object));
        } else {
            throw new IllegalArgumentException("object为空");
        }
    }

    public RevokeKeyReq(ASN1Sequence sequence) {
        if (sequence.size() != 1) {
            throw new IllegalArgumentException("RevokeKeyReq结构size错误");
        } else {
            this.userCertNo = ASN1Integer.getInstance(sequence.getObjectAt(0));
        }
    }


    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector(1);
        vector.add(this.userCertNo);
        return new DERSequence(vector);
    }
}
