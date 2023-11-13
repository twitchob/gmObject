package com.zzy.GMT0014.caAndKm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Date;

/**
 * @author zzypersonally@gmail.com
 * @description  GMT0014 5.2.3.2 Page8  申请包中对应用户信息
 * @since 2023/10/9 10:00
 */
@Getter
@AllArgsConstructor
public class AppUserInfo  extends ASN1Object {
    private ASN1Integer userCertNo;          //终端用户加密证书序列号  对于同一个CA,该序列号唯一,如果需要
    private SubjectPublicKeyInfo userPubKey; //终端用户保密公钥
    private ASN1GeneralizedTime notBefore;   //密钥有效期起始时间
    private ASN1GeneralizedTime notAfter;    //密钥有效期截止时间
    private DEROctetString userName;         //用户姓名
    private DEROctetString dsCode;           //地区代码 可选 如果此项存在，必须保存到库中
    private DEROctetString extendInfo;       //扩展信息 可选


    public AppUserInfo(BigInteger userCertNo, PublicKey signPublicKey, Date notBofore, Date notAfter, String userName, String dsCode, String extendInfo) throws IOException {
        this.userCertNo = new ASN1Integer(userCertNo);
        this.userPubKey = SubjectPublicKeyInfo.getInstance(signPublicKey.getEncoded());
        this.notBefore = new DERGeneralizedTime(notBofore);
        this.notAfter = new DERGeneralizedTime(notAfter);
        if (userName != null) {
            this.userName = new DEROctetString((new X500Name(userName)).getEncoded());
        }
        if (dsCode != null) {
            this.dsCode = new DEROctetString(dsCode.getBytes());
        }
        if (extendInfo != null) {
            this.extendInfo = new DEROctetString(extendInfo.getBytes());
        }
    }
     
    /**
     * 解码
     */
    public AppUserInfo(ASN1Sequence seq) {
        if (seq.size() < 4) {
            throw new IllegalArgumentException("wrong size for AppUserInfo");
        } else {
            this.userCertNo = ASN1Integer.getInstance(seq.getObjectAt(0));
            this.userPubKey = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(1));
            this.notBefore = ASN1GeneralizedTime.getInstance(seq.getObjectAt(2));
            this.notAfter = ASN1GeneralizedTime.getInstance(seq.getObjectAt(3));

            for (int extras = seq.size() - 4; extras > 0; --extras) {
                DLTaggedObject extra = (DLTaggedObject) seq.getObjectAt(3 + extras);
                switch (extra.getTagNo()) {
                case 0:
                    this.userName = (DEROctetString) DEROctetString.getInstance(extra.getBaseObject());
                    break;
                case 1:
                    this.dsCode = (DEROctetString) DEROctetString.getInstance(extra.getBaseObject());
                    break;
                case 2:
                    this.extendInfo = (DEROctetString) DEROctetString.getInstance(extra.getBaseObject());
                }
            }
        }
    }

    public static AppUserInfo getInstance(Object obj) {
        if (obj instanceof AppUserInfo) {
            return (AppUserInfo) obj;
        } else if (obj != null) {
            return new AppUserInfo(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }
    /**
     * 编码
     */
    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.userCertNo);
        v.add(this.userPubKey);
        v.add(this.notBefore);
        v.add(this.notAfter);
        if (this.userName != null) {
            v.add(new DERTaggedObject(true, 0, this.userName));
        }
        if (this.dsCode != null) {
            v.add(new DERTaggedObject(true, 1, this.dsCode));
        }
        if (this.extendInfo != null) {
            v.add(new DERTaggedObject(true, 2, this.extendInfo));
        }
        return new DERSequence(v);
    }
}
