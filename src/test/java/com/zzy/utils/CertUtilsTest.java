package com.zzy.utils;

import org.junit.Test;

import java.security.cert.X509Certificate;

import static org.junit.Assert.*;

public class CertUtilsTest {

    @Test
    public void  test() throws Exception{
        String s = "MIICYDCCAgagAwIBAgIEBeZ23DAKBggqgRzPVQGDdTCBizELMAkGA1UEBhMCQ04xETAPBgNVBAgMCHNoYW5kb25nMQ4wDAYDVQQHDAVqaW5hbjENMAsGA1UECgwEc3phZjERMA8GA1UECwwIaW5mb1NhZmUxDzANBgNVBAMMBmNvbS5hZjEmMCQGCSqGSIb3DQEJARYXenp5cGVyc29uYWxseUBnbWFpbC5jb20wHhcNMjMxMTE2MDgxMDEwWhcNNDMxMTExMDgxMDEwWjBjMQswCQYDVQQGEwJDTjERMA8GA1UECAwIc2hhbmRvbmcxDjAMBgNVBAcMBWppbmFuMQ0wCwYDVQQKDARzemFmMREwDwYDVQQLDAhpbmZvU2FmZTEPMA0GA1UEAwwGY29tLmFmMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEHsfAuRT6R01XBBBvmfQ2rd8patR4/7evieukZqY6DEgQBHuewg/HwQB+LTpQYbl5WB0jiCzklOhAVy1V6/RP+qN/MH0wHQYDVR0OBBYEFPxDwtIpf/4LyAEGmkQkEnPPhecrMA8GA1UdEwEB/wQFMAMBAf8wDgYDVR0PAQH/BAQDAgEGMCIGA1UdEQQbMBmBF3p6eXBlcnNvbmFsbHlAZ21haWwuY29tMBcGA1UdHwQQMA4wDKAKoAiGBmNybFVybDAKBggqgRzPVQGDdQNIADBFAiEAofKUquylMwzlVgBIEk5tmyRounI5FbEsrZ8v7nBo9d8CIHe2LGdqCMw7FD9rLFmSEfsBO+W9ggW2BIWZzCdndzix";
        X509Certificate x509Certificate = CertUtils.readCert(s);
        System.out.println(x509Certificate);

    }

}