package com.zzy.GMT0014.caAndKm.respond;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 17:21
 */
@Getter
@AllArgsConstructor
public class Respond extends ASN1Object implements ASN1Choice {
    private int tagNo;
    private RetKeyRespond applyKeyRespond;                 //申请密钥响应
    private RetKeyRespond restoreKeyRespond;               //恢复密钥响应
    private RevokeKeyRespond revokeKeyRespond;             //注销密钥响应
    private ErrorPkgRespond errorPkgRes;                   //错误包响应

    public static Respond getInstance(Object o) {
        if (o instanceof Respond) {
            return (Respond) o;
        } else {
            return o instanceof ASN1TaggedObject ? new Respond((ASN1TaggedObject) o) : null;
        }
    }

    private Respond(ASN1TaggedObject tagged) {
        if (tagged.getTagNo() == 0) {
            this.applyKeyRespond = RetKeyRespond.getInstance(tagged.getObject());
            this.tagNo = 0;
        } else if (tagged.getTagNo() == 1) {
            this.restoreKeyRespond = RetKeyRespond.getInstance(tagged.getObject());
            this.tagNo = 1;
        } else if (tagged.getTagNo() == 2) {
            this.revokeKeyRespond = RevokeKeyRespond.getInstance(tagged.getObject());
            this.tagNo = 2;
        } else {
            if (tagged.getTagNo() != 3) {
                throw new IllegalArgumentException("unknown tag: " + tagged.getTagNo());
            }
            this.errorPkgRes = ErrorPkgRespond.getInstance(tagged.getObject());
            this.tagNo = 3;
        }

    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        if (this.applyKeyRespond != null) {
            return new DERTaggedObject(false, 0, this.applyKeyRespond);
        } else if (this.restoreKeyRespond != null) {
            return new DERTaggedObject(false, 1, this.restoreKeyRespond);
        } else {
            return this.revokeKeyRespond != null ?
                    new DERTaggedObject(false, 2, this.revokeKeyRespond)
                    : new DERTaggedObject(false, 3, this.errorPkgRes);
        }
    }
}
