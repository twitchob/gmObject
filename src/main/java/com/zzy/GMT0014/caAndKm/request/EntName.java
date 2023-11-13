package com.zzy.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;

import java.math.BigInteger;

/**
 * @author zzypersonally@gmail.com
 * @description
 * @since 2023/10/9 11:00
 */
@Getter
@AllArgsConstructor
public class EntName extends ASN1Object {
    private AlgorithmIdentifier hashAlgorithm;
    private GeneralName entName;
    private DEROctetString entPubKeyHash;
    private ASN1Integer serialNumber;

    public EntName(ASN1Sequence instance) {
        this.hashAlgorithm = AlgorithmIdentifier.getInstance(instance.getObjectAt(0));
        this.entName = GeneralName.getInstance(instance.getObjectAt(1));
        this.entPubKeyHash = (DEROctetString) DEROctetString.getInstance(instance.getObjectAt(2));
        this.serialNumber = ASN1Integer.getInstance(instance.getObjectAt(3));
    }

    public EntName(String caHashAlgorithmOID, String caSubjectInfo, byte[] caPubKeyHash, BigInteger caSerialNum) {
        this.hashAlgorithm = new AlgorithmIdentifier(new ASN1ObjectIdentifier(caHashAlgorithmOID));
        this.entName = new GeneralName(GeneralName.dNSName, caSubjectInfo);
        this.entPubKeyHash = new DEROctetString(caPubKeyHash);
        this.serialNumber = new ASN1Integer(caSerialNum);
    }

    public static EntName getInstance(Object objectAt) {
        if (objectAt instanceof EntName) {
            return (EntName) objectAt;
        } else {
            return new EntName(ASN1Sequence.getInstance(objectAt));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(4);
        v.add(this.hashAlgorithm);
        v.add(this.entName);
        v.add(this.entPubKeyHash);
        v.add(this.serialNumber);
        return new DERSequence(v);
    }
}
