package com.af.ca.GM.GMT0014.caAndKm.respond;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 17:12
 */
@Getter
@AllArgsConstructor
public class KMRespond  extends ASN1Object {
    private KSRespond tbsRespond;
    private AlgorithmIdentifier signstureAlgorithm;
    private DEROctetString signstureValue;


    public static KMRespond getInstance(Object obj) {
        if (obj instanceof KMRespond) {
            return (KMRespond)obj;
        } else if (obj != null) {
            return new KMRespond(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }

    public KMRespond(ASN1Sequence seq) {
        if (seq.size() != 3) {
            throw new IllegalArgumentException("wrong size for KMCRespond");
        } else {
            this.tbsRespond = KSRespond.getInstance(seq.getObjectAt(0));
            this.signstureAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            this.signstureValue = (DEROctetString)DEROctetString.getInstance(seq.getObjectAt(2));
        }
    }


    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.tbsRespond);
        v.add(this.signstureAlgorithm);
        v.add(this.signstureValue);
        return new DERSequence(v);
    }
}
