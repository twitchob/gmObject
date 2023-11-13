package com.zzy.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 9:58
 */
@Getter
@AllArgsConstructor
public class ApplyKeyReq extends ASN1Object {

    private AlgorithmIdentifier appKeyType;     //要申请的加密密钥对类型
    private ASN1Integer appKeyLen;              //要申请的密钥长度
    private AlgorithmIdentifier retAsymAlg;     //KM返回的非对称算法标识
    private AlgorithmIdentifier retSymAlg;      //KM返回的对称算法标识
    private AlgorithmIdentifier retHashAlg;     //KM返回的杂凑算法标识
    private AppUserInfo appUserInfo;            //用户信息


    public static ApplyKeyReq getInstance(Object obj) {
        if (obj instanceof ApplyKeyReq) {
            return (ApplyKeyReq) obj;
        } else if (obj != null) {
            return new ApplyKeyReq(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }

    public ApplyKeyReq(ASN1Sequence seq) {
        if (seq.size() != 6) {
            throw new IllegalArgumentException("wrong size for ApplyKeyReq");
        } else {
            this.appKeyType = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
            this.appKeyLen = ASN1Integer.getInstance(seq.getObjectAt(1));
            this.retAsymAlg = AlgorithmIdentifier.getInstance(seq.getObjectAt(2));
            this.retSymAlg = AlgorithmIdentifier.getInstance(seq.getObjectAt(3));
            this.retHashAlg = AlgorithmIdentifier.getInstance(seq.getObjectAt(4));
            this.appUserInfo = AppUserInfo.getInstance(seq.getObjectAt(5));
        }
    }

    /**
     * 编码
     */
    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(6);
        v.add(this.appKeyType);
        v.add(this.appKeyLen);
        v.add(this.retAsymAlg);
        v.add(this.retSymAlg);
        v.add(this.retHashAlg);
        v.add(this.appUserInfo);
        return new DERSequence(v);
    }
}
