package com.project.client.services;

import com.project.client.entity.ClientKeys;

import java.math.BigInteger;

public interface CommonFunctionInterface {

    String calculateHash(String data) throws Exception;

    byte[] encryptData(String data, BigInteger modulus, BigInteger expo) throws Exception;

    String decryptData(byte[] data, BigInteger modulus, BigInteger expo) throws Exception;

    <T> T convertJsonToJava(String jsonString, Class<T> obj) throws Exception;

    String convertJavaToJson(Object object) throws Exception;

    ClientKeys getKeysFromDB(String collectionName) throws Exception;
}
