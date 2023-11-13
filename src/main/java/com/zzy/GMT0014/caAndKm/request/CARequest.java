package com.zzy.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/**
 * @author zzypersonally@gmail.com
 * @description GMT0014 5.2.3.1 Page6  密钥申请协议 请求数据格式
 * @since 2023/10/9 10:55
 */
@Getter
@AllArgsConstructor
public class CARequest extends ASN1Object {
    private KSRequest ksRequest;                     //密钥申请服务请求
    private AlgorithmIdentifier signatureAlgorithm;  //签名算法
    private DEROctetString signatureValue;           //请求信息的签名


    public static CARequest getInstance(Object obj) {
        if (obj instanceof CARequest) {
            return (CARequest) obj;
        } else if (obj != null) {
            return new CARequest(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }

    public CARequest(ASN1Sequence seq) {
        if (seq.size() != 3) {
            throw new IllegalArgumentException("wrong size for CARequest");
        } else {
            this.ksRequest = KSRequest.getInstance(seq.getObjectAt(0));
            this.signatureAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            this.signatureValue = (DEROctetString) DEROctetString.getInstance(seq.getObjectAt(2));
        }
    }




    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(3);
        v.add(this.ksRequest);
        v.add(this.signatureAlgorithm);
        v.add(this.signatureValue);
        return new DERSequence(v);
    }
}
