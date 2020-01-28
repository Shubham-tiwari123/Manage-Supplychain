package com.project.server.services;

import com.project.server.entity.ServerKeys;

import java.math.BigInteger;

public interface CommonFunctionsInterface {

    String generateSignature() throws Exception;

    String calculateHash(String value) throws Exception;

    byte[] encryptData(String data, BigInteger modulus, BigInteger expo) throws Exception;

    String decryptData(byte[] data, BigInteger modulus, BigInteger expo) throws Exception;

    <T> T convertJsonToJava(String jsonString, Class<T> obj) throws Exception;

    String convertJavaToJson(Object object) throws Exception;

}