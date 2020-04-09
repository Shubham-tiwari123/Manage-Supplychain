package com.example.shipchain.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.Cipher;

public class CommonFunction {


    /*public <T> T convertJsonToJava(String jsonString, Class<T> obj) throws Exception {
        T result=null;
        result = mapper.readValue(jsonString,obj);
        return result;
    }

    public String convertJavaToJson(Object object) throws Exception {
        String jsonResult = null;
        jsonResult = mapper.writeValueAsString(object);
        return jsonResult;
    }*/

    public String calculateHash(String value) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHashValue = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashValue = new StringBuilder(2 * encodedHashValue.length);
        for (byte b : encodedHashValue) {
            hashValue.append(b);
        }
        return hashValue.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
}

