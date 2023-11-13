package com.af.asn1.GMT0010.fundamentalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.*;

//6.9

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 0010 6.9
 * @since 2023/9/12 18:05
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Version extends ASN1Object {
    ASN1Integer version;

    public static Version getInstance(Object objectAt) {
        if (objectAt instanceof Version) {
            return (Version) objectAt;
        } else {
            return new Version(ASN1Integer.getInstance(((DLSequence)objectAt).getObjectAt(0)));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(1);
        v.add(version);
        return new DERSequence(v);
    }


}
