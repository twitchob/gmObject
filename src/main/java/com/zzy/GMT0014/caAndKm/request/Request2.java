package com.zzy.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;

/**
 * @author zzypersonally@gmail.com
 * @description 当前类没有使用    Request类型为CHOICE   本类模仿 BC库中CHOICE 类型的定义 {@link org.bouncycastle.asn1.x509.GeneralName}
 * @since 2023/10/11 11:56
 */
@Getter
@AllArgsConstructor
public class Request2 extends ASN1Object implements ASN1Choice {


    //全部枚举
    public static final int applyKeyReq = 0;
    public static final int restoreKeyReq = 1;
    public static final int revokeKeyReq = 2;


    private ASN1Encodable obj;
    private int tag;


    @Override
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, tag, obj);

    }
}
