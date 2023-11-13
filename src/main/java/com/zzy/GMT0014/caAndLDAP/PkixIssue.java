package com.af.ca.GM.GMT0014.caAndLDAP;


import com.af.securityAccess.asn1.x509.AlgorithmIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ocsp.Signature;

/**
 * @author zzypersonally@gmail.com
 * @description  GMT0014 5.3.2 Page11 ca 与 LDAP 服务间的相关协议 发布协议
 * @since 2023/11/2 16:19
 */
@Getter
@AllArgsConstructor
public class PkixIssue  extends ASN1Object {
    private TBSIssue pkixIssueInfo;
    private AlgorithmIdentifier signatureAlgorithm;
    private Signature CASignature;


    //getInstance


    //构造
    @Override
    public ASN1Primitive toASN1Primitive() {
        return null;
    }
}
