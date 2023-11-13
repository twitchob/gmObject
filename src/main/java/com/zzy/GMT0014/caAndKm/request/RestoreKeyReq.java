package com.af.ca.GM.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

import java.math.BigInteger;
import java.security.PublicKey;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 16:22
 */
@Getter
@AllArgsConstructor
public class RestoreKeyReq extends ASN1Object {

    private AlgorithmIdentifier retAsymAlg;  //KM返回的非对算法标识
    private AlgorithmIdentifier retSymAlg;   //KM返回的对称算法标识
    private AlgorithmIdentifier retHashAlg;  //KM返回的杂凑算法标识
    private ASN1Integer userCertNo;          //指定用户证书序列号
    private SubjectPublicKeyInfo userPubKey;       //指定用户签名公钥

    public RestoreKeyReq(AlgorithmIdentifier retAsymAlg, AlgorithmIdentifier retSymAlg, AlgorithmIdentifier retHashAlg, BigInteger userCertNo, PublicKey userPubKey) {
        this.retAsymAlg = retAsymAlg;
        this.retSymAlg = retSymAlg;
        this.retHashAlg = retHashAlg;
        this.userCertNo = new ASN1Integer(userCertNo);
        this.userPubKey = SubjectPublicKeyInfo.getInstance(userPubKey.getEncoded());
    }

    public static RestoreKeyReq getInstance(Object obj) {
        if (obj instanceof RestoreKeyReq) {
            return (RestoreKeyReq) obj;
        } else if (obj != null) {
            return new RestoreKeyReq(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }

    public RestoreKeyReq(ASN1Sequence seq) {
        if (seq.size() != 5) {
            throw new IllegalArgumentException("wrong size for RestoreKeyReq");
        } else {
            this.retAsymAlg = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
            this.retSymAlg = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            this.retHashAlg = AlgorithmIdentifier.getInstance(seq.getObjectAt(2));
            this.userCertNo = ASN1Integer.getInstance(seq.getObjectAt(3));
            this.userPubKey = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(4));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector(5);
        vector.add(this.retAsymAlg);
        vector.add(this.retSymAlg);
        vector.add(this.retHashAlg);
        vector.add(this.userCertNo);
        vector.add(this.userPubKey);
        return new DERSequence(vector);

    }
}
