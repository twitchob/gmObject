package com.af.ca.GM.GMT0014.caAndLDAP;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.Certificate;

/**
 * @author zzypersonally@gmail.com
 * @description GMT0014 5.3.2 Page11 ca 与 LDAP 服务间的相关协议 发布协议
 * @since 2023/11/2 16:50
 */
@Getter
@AllArgsConstructor
public class TBSIssue {

    /**
     * 版本 目前为1
     */
    private  final ASN1Integer version = new ASN1Integer(1L);

    /**
     * 0:向LDAP或OCSP更新根证书
     * 1:向LDAP发送证书
     * 2:向LDAP发送作废证书序列号
     * 3:向LDAP发送作废证书链
     * 4:向LDAP发送证书状态
     * 5:向LDAP发送交叉认证证书,LDAP自己用根证书与此包中的第一个证书组成PKCS#7证书发布链
     */
    private  final ASN1Integer type;

    /**
     * 包内随机数
     */
    private DEROctetString transNonce;

    /**
     * 包内证书 或 证书状态 或 证书撤销链 数目
     */
    private ASN1Integer number;

    /**
     * 接收方比较时间
     */
    private ASN1GeneralizedTime time;

    private Certificate [] cert;






}
