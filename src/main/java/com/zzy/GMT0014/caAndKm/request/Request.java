package com.af.ca.GM.GMT0014.caAndKm.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.asn1.*;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 16:42
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Request extends ASN1Object implements ASN1Choice {
    private int tagNo;
    private ApplyKeyReq applyKeyReq;         //申请密钥 tagNo = 0
    private RestoreKeyReq restoreKeyReq;     //恢复密钥 tagNo = 1
    private RevokeKeyReq revokeKeyReq;       //销毁密钥 tagNo = 2

    public Request(ApplyKeyReq applyKeyReq) {
        if (applyKeyReq == null) {
            throw new IllegalArgumentException("applyKeyReq不可为空");
        } else {
            this.applyKeyReq = applyKeyReq;
        }
    }

    public Request(RestoreKeyReq restoreKeyReq) {
        if (restoreKeyReq == null) {
            throw new IllegalArgumentException("restoreKeyReq不可为空");
        } else {
            this.restoreKeyReq = restoreKeyReq;
        }
    }

    public Request(RevokeKeyReq revokeKeyReq) {
        if (revokeKeyReq == null) {
            throw new IllegalArgumentException("revokeKeyReq不可为空");
        } else {
            this.revokeKeyReq = revokeKeyReq;
        }
    }

    private Request(ASN1TaggedObject taggedObject) {
        if (taggedObject.getTagNo() == 0) {
            this.applyKeyReq = ApplyKeyReq.getInstance(taggedObject.getObject());
            this.tagNo = 0;
        } else if (taggedObject.getTagNo() == 1) {
            this.restoreKeyReq = RestoreKeyReq.getInstance(taggedObject.getObject());
            this.tagNo = 1;
        } else {
            if (taggedObject.getTagNo() != 2) {
                throw new IllegalArgumentException("无法识别的TAG ：" + taggedObject.getTagNo());
            }
            this.revokeKeyReq = RevokeKeyReq.getInstance(taggedObject.getObject());
            this.tagNo = 2;
        }
    }

    public static Request getInstance(Object object) {
        if (object instanceof Request) {
            return (Request) object;
        } else if (object != null) {
            return new Request(ASN1TaggedObject.getInstance(object));
        } else {
            throw new IllegalArgumentException("object is null");
        }
//        平台代码  跑不起来  解码不出来
//        if (object instanceof Request) {
//            return (Request) object;
//        } else {
//            return object instanceof ASN1TaggedObject ? new Request((ASN1TaggedObject) object) : null;
//        }
    }


    @Override
    public ASN1Primitive toASN1Primitive() {
        if (this.applyKeyReq != null) {
            return new DERTaggedObject(false, 0, this.applyKeyReq);
//            return ASN1Sequence.getInstance(this.applyKeyReq);        //平台代码  跑不起来  编码后解码不出来 并且和sec不一致, 我认为上一行代码是正确的
        } else {
            return this.restoreKeyReq != null ? new DERTaggedObject(false, 1, this.restoreKeyReq)
                    : new DERTaggedObject(false, 2, this.revokeKeyReq);
        }
    }
}
