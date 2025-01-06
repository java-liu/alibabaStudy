package com.ljava.auth.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class RequestWithEncrypt {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) {
        // 业务数据
        String businessData = "{\"selfTerminalCode\":\"x2222\"}";

        // 加密后数据
        printEncrypted(businessData);
    }

    private static void printEncrypted(String businessData) {
        X509Certificate certificate = readCertificate("MIIBsDCCAVWgAwIBAgIGAZQVa7oaMAoGCCqBHM9VAYN1MHwxCzAJBgNVBAYTAkNOMU8wTQYDVQQDDEZCSkdYQOS4reWbveaLm+agh+WFrOWFseacjeWKoeW5s+WPsOaciemZkOWFrOWPuEBOTjkxMTEwMTA4MDg5NjM4MTlYUUAxMRwwGgYDVQQpDBNOOTExMTAxMDgwODk2MzgxOVhRMB4XDTI0MTIzMDAyMzYwOVoXDTI1MTIzMDAyMzYwOVowQTEbMBkGA1UEBRMSOTExMTAxMDhNQTAxVDJBQjU4MRUwEwYDVQQKDAzkuK3mi5vkupLov54xCzAJBgNVBAYTAkNOMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEv5oKuTzC7YDlGnmXyoGdwP/2R7T3R1o34Y6/1vlI8Mi4wv9GStIlBnc07lXWHGsc0DqihzqdF+DBibTnqr44jTAKBggqgRzPVQGDdQNJADBGAiEAsB1cd40kpqUymxfGcC+WMgIbLoeOHKYwYyIjua94X4MCIQCqOmSoWk3v5RcRs35or8Y9XY52J0hEdLi5WYgdUDNptg==");
        SM2 sm2 = new SM2(null, Base64.encode(certificate.getPublicKey().getEncoded()));
        System.out.println(sm2.encryptHex(businessData.getBytes(), KeyType.PublicKey));
    }

    private static X509Certificate readCertificate(String subCertBase64) {
        try {
            byte[] certBytes = Base64.decode(subCertBase64);
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
            return (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certBytes));
        } catch (Exception exception) {
            return null;
        }
    }

}
