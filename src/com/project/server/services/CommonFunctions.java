package com.project.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.server.entity.ServerKeys;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class CommonFunctions implements CommonFunctionsInterface {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String generateSignature() throws Exception {
        return null;
    }

    @Override
    public String calculateHash(String value) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHashValue = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashValue = new StringBuilder(2 * encodedHashValue.length);
        for (byte b : encodedHashValue) {
            hashValue.append(b);
        }
        return hashValue.toString();
    }

    @Override
    public byte[] encryptData(String data, BigInteger modulus, BigInteger expo) throws Exception {
        byte[] dataToEncrypt = data.getBytes(StandardCharsets.UTF_8);
        System.out.println("data size:" + dataToEncrypt.length);
        byte[] encryptedData = null;
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, expo);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = factory.generatePublic(rsaPublicKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        encryptedData = cipher.doFinal(dataToEncrypt);
        return encryptedData;
    }

    @Override
    public String decryptData(byte[] data, BigInteger modulus, BigInteger expo) throws Exception {
        byte[] decryptedData;
        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, expo);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = factory.generatePrivate(rsaPrivateKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        decryptedData = cipher.doFinal(data);
        String value = new String(decryptedData, StandardCharsets.UTF_8);
        return value;
    }

    @Override
    public <T> T convertJsonToJava(String jsonString, Class<T> obj) throws Exception {
        T result=null;
        result = mapper.readValue(jsonString,obj);
        return result;
    }

    @Override
    public String convertJavaToJson(Object object) throws Exception {
        String jsonResult = null;
        jsonResult = mapper.writeValueAsString(object);
        return jsonResult;
    }

}
