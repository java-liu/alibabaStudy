package com.ljava.auth.utils;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyGenerator {
    public static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static void saveKeys(KeyPair keyPair) throws IOException {
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 将私钥和公钥转换为Base64编码的字符串
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        // 保存私钥到文件
        File privateKeyFile = new File("private.key");
        try (FileOutputStream fos = new FileOutputStream(privateKeyFile)) {
            fos.write(encodedPrivateKey.getBytes());
        }

        // 保存公钥到文件
        File publicKeyFile = new File("public.key");
        try (FileOutputStream fos = new FileOutputStream(publicKeyFile)) {
            fos.write(encodedPublicKey.getBytes());
        }
    }

    public void savePublicKey() {
        // 使用类加载器获取资源目录下的文件路径
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("public.key");
        if (inputStream == null) {
            throw new IllegalArgumentException("文件未找到");
        }

        // 如果需要将输入流转换为文件对象，可以这样做
        File publicKeyFile = new File(getClass().getClassLoader().getResource("public.key").getFile());
        // 继续处理 publicKeyFile
    }

    public static KeyPair loadKeys() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // 从文件中读取私钥
        File privateKeyFile = new File("private.key");
        byte[] privateKeyBytes = new byte[(int) privateKeyFile.length()];
        try (FileInputStream fis = new FileInputStream(privateKeyFile)) {
            fis.read(privateKeyBytes);
        }
        String encodedPrivateKey = new String(privateKeyBytes);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodedPrivateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // 从文件中读取公钥
        File publicKeyFile = new File("public.key");
        byte[] publicKeyBytes = new byte[(int) publicKeyFile.length()];
        try (FileInputStream fis = new FileInputStream(publicKeyFile)) {
            fis.read(publicKeyBytes);
        }
        String encodedPublicKey = new String(publicKeyBytes);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(encodedPublicKey));
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateRsaKeyPair();
            saveKeys(keyPair);
            KeyPair loadedKeyPair = loadKeys();
            System.out.println("公钥: " + loadedKeyPair.getPublic().toString());
            System.out.println("私钥: " + loadedKeyPair.getPrivate().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
