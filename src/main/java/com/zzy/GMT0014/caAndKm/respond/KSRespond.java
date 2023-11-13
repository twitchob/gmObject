package com.zzy.GMT0014.caAndKm.respond;

import com.af.ca.GM.GMT0014.caAndKm.request.EntName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 17:15
 */
@Getter
@AllArgsConstructor
public class KSRespond extends ASN1Object {

    private ASN1Integer version;              //版本 1
    private EntName kmName;
    private ASN1Sequence respondList;
    private ASN1GeneralizedTime respondTime;
    private ASN1Integer taskNo;


    //构造
    public KSRespond(ASN1Sequence sequence) {
        if (sequence.size() != 5) {
            throw new IllegalArgumentException("KSRespond结构size错误");
        } else {
            ASN1Integer version = ASN1Integer.getInstance(sequence.getObjectAt(0));
            if (version.getValue().intValue() != 1) {
                throw new IllegalArgumentException("KSRespond version 不是1");
            } else {
                this.kmName = EntName.getInstance(sequence.getObjectAt(1));
                this.respondList = ASN1Sequence.getInstance(sequence.getObjectAt(2));
                this.respondTime = DERGeneralizedTime.getInstance(sequence.getObjectAt(3));
                this.taskNo = ASN1Integer.getInstance(sequence.getObjectAt(4));
            }
        }
    }


    //getInstance
    public static KSRespond getInstance(Object object) {
        if (object instanceof KSRespond) {
            return (KSRespond) object;
        } else if (object != null) {
            return new KSRespond(ASN1Sequence.getInstance(object));
        } else {
            throw new IllegalArgumentException("object 为空");
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector(5);
        vector.add(version);
        vector.add(this.kmName);
        vector.add(this.respondList);
        vector.add(this.respondTime);
        vector.add(this.taskNo);
        return new DERSequence(vector);
    }
}
