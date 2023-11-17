package com.zzy.utils;

import cn.hutool.core.codec.Base64;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @author zzypersonally@gmail.com
 * @description 证书工具类
 * @since 2023/11/16 17:59
 */
public class CertUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Base64 证书 读取为 X509Certificate
     */
    public static X509Certificate readCert(String cert) throws CertificateException, NoSuchProviderException {

        //去掉头尾
        String certStr = cert.replaceAll("-----BEGIN CERTIFICATE-----", "")
                .replaceAll("-----END CERTIFICATE-----", "")
                .replaceAll("\n", "");
        //base64解码
        byte[] certBytes = Base64.decode(certStr);
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
        return (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certBytes));
    }
}
