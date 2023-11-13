package com.zzy.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;

/**
 * @author zzypersonally@gmail.com
 * @description GMT0014 5.2.3.1 Page6  密钥申请服务请求
 * @since 2023/10/9 10:57
 */
@Getter
@AllArgsConstructor
public class KSRequest extends ASN1Object {
    private static final ASN1Integer version = new ASN1Integer(1L);                 //版本 1
    private EntName caName;
    private ASN1Sequence requestList;
    private ASN1GeneralizedTime requestTime;
    private ASN1Integer taskNo;                                                          //任务编号 唯一



    public static KSRequest getInstance(Object object) {
        if (object instanceof KSRequest) {
            return (KSRequest) object;
        } else if (object != null) {
            return new KSRequest(ASN1Sequence.getInstance(object));
        } else {
            throw new IllegalArgumentException("object 为空");
        }
    }

    public KSRequest(ASN1Sequence sequence) {
        if (sequence.size() != 5) {
            throw new IllegalArgumentException("KSRequest结构size错误");
        } else {
            ASN1Integer version = ASN1Integer.getInstance(sequence.getObjectAt(0));
            if (version.getValue().intValue() != 1) {
                throw new IllegalArgumentException("KSRequest version 不是1");
            } else {
                this.caName = EntName.getInstance(sequence.getObjectAt(1));
                this.requestList = ASN1Sequence.getInstance(sequence.getObjectAt(2));
                this.requestTime = DERGeneralizedTime.getInstance(sequence.getObjectAt(3));
                this.taskNo = ASN1Integer.getInstance(sequence.getObjectAt(4));
            }
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector(5);
        vector.add(version);
        vector.add(this.caName);
        vector.add(this.requestList);
        vector.add(this.requestTime);
        vector.add(this.taskNo);
        return new DERSequence(vector);
    }
}
