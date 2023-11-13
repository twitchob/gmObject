package com.zzy.GMT0010;

import com.af.asn1.GMT0010.fundamentalType.DigestAlgorithmIdentifier;
import com.af.asn1.GMT0010.fundamentalType.DigestEncryptionAlgorithmIdentifier;
import com.af.asn1.GMT0010.fundamentalType.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.IssuerAndSerialNumber;

import java.util.Enumeration;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/12 17:39
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignerInfo extends ASN1Object {

    //版本
    private Version version;
    //issuerAndSerialNumber
    private IssuerAndSerialNumber issuerAndSerialNumber;
    //摘要算法标识符
    private DigestAlgorithmIdentifier digestAlgorithmIdentifier;
    //attribute
    private ASN1Set authenticatedAttributes;
    //SM2-1椭圆曲线 签名算法标识符
    private DigestEncryptionAlgorithmIdentifier digestEncryptionAlgorithmIdentifier;
    //签名值
    private ASN1OctetString encryptedDigest;


    /**
     * @param instance
     */
    public SignerInfo(ASN1Sequence instance) {
        Enumeration objects = instance.getObjects();
        this.version = Version.getInstance(objects.nextElement());
        this.issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(objects.nextElement());
        this.digestAlgorithmIdentifier = DigestAlgorithmIdentifier.getInstance(objects.nextElement());
        Object nextElement = objects.nextElement();
        if (nextElement instanceof ASN1TaggedObject) {
            this.authenticatedAttributes = ASN1Set.getInstance((ASN1TaggedObject) nextElement, false);
            this.digestEncryptionAlgorithmIdentifier = DigestEncryptionAlgorithmIdentifier.getInstance(objects.nextElement());
        } else {
            this.authenticatedAttributes = null;
            this.digestEncryptionAlgorithmIdentifier = DigestEncryptionAlgorithmIdentifier.getInstance(nextElement);
        }
        this.encryptedDigest = ASN1OctetString.getInstance(objects.nextElement());


    }

    public static SignerInfo getInstance(Object object) {
        if (object instanceof SignerInfo) {
            return (SignerInfo) object;
        } else if (object != null) {
            return new SignerInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }


    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(version);
        v.add(issuerAndSerialNumber);
        v.add(digestAlgorithmIdentifier);
        if (authenticatedAttributes != null) {
            v.add(authenticatedAttributes);
        }
        v.add(digestEncryptionAlgorithmIdentifier);
        v.add(encryptedDigest);
        return new DERSequence(v);
    }
}
